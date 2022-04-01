package com.dream.server.service;

import com.dream.container.anno.Assign;
import com.dream.server.config.ServiceMark;
import com.dream.server.config.SyncCategory;
import com.dream.server.database.model.Player;
import com.dream.server.param.*;
import com.dream.server.service.component.CChannels;
import com.dream.server.service.component.CPlayer;
import com.dream.server.sync.Sync;
import com.dream.server.sync.Synchronizer;
import com.dream.service.anno.Mark;
import com.dream.service.anno.Service;
import com.dream.service.auth.AuthenticatedInfo;
import com.dream.service.bound.AuthenticateHandler;
import com.dream.service.codec.SuccessOrFailure;
import io.netty.channel.Channel;


@Service
public class PlayerService
{
    @Assign
    private CPlayer cPlayer;

    @Sync(SyncCategory.DELIVER_TASK)
    private Synchronizer syncDeliverTask;

    @Sync(SyncCategory.BUY_ITEM)
    private Synchronizer syncBuyItem;

    @Mark(value = 2, authenticate = false)
    public SuccessOrFailure login(LLogin parameter, Channel channel)
    {
        Player player = cPlayer.login(parameter, channel);

        if (player == null)
        {
            return SuccessOrFailure.FAIL;
        }

        return SuccessOrFailure.buildSuccess(new SPrimitive<>(player.getPlayerId()));
    }

    /**
     * 服务器与编辑器需要使用的版本
     * @param param     查询参数
     * @return          result
     */
    @Mark(27)
    public SuccessOrFailure requestPlayerInfoForServer(LQueryPlayerInfo param)
    {
        return SuccessOrFailure.buildSuccess(cPlayer.queryPlayerInfo(param.getPlayerId(), param.getCondition()));
    }

    @Mark(10)
    public SuccessOrFailure requestPlayerInformation(int condition, AuthenticatedInfo info)
    {
        return SuccessOrFailure.buildSuccess(cPlayer.queryPlayerInfo(info.getPlayerId(), condition));
    }

    @Mark(18)
    public SuccessOrFailure equipWeapon(LEquipWeapon param, AuthenticatedInfo info)
    {
        return SuccessOrFailure.buildSuccess(new PPlayerWeapon(cPlayer.equipWeapon(param, info)));
    }

    @Mark(16)
    public SuccessOrFailure equipModule(LEquipModule param, AuthenticatedInfo info)
    {
        return SuccessOrFailure.buildSuccess(new PPlayerModule(cPlayer.equipModule(param, info)));
    }

    @Mark(22)
    public SuccessOrFailure requestTaskList(LRequestTask param, AuthenticatedInfo info)
    {
        return SuccessOrFailure.buildSuccess(cPlayer.requestTaskList(param, info));
    }


    @Mark(value = 13)
    public SuccessOrFailure acceptTask(long ptid, AuthenticatedInfo info)
    {
        return cPlayer.acceptTask(ptid, info) ? SuccessOrFailure.SUCCESS : SuccessOrFailure.FAIL;
    }

    @Mark(value = 15)
    public SuccessOrFailure updateTrackingState(LUpdateTaskTracking param, AuthenticatedInfo info)
    {
        return SuccessOrFailure.build(cPlayer.updateTrackingState(param, info));
    }

    @Mark(value = 12)
    public SuccessOrFailure deliverTask(long ptid, Channel channel)
    {
        AuthenticatedInfo info = channel.attr(AuthenticateHandler.AUTHORIZATION_KEY).get();

        // todo 使用锁应该会好点
        if (!syncDeliverTask.acquireSyncMark(info.getPlayerId()))
        {
            return SuccessOrFailure.FAIL;
        }

        try
        {
            return SuccessOrFailure.build(cPlayer.deliverTask(ptid, info.getPlayerId(), channel));
        }
        finally
        {
            syncDeliverTask.deferredReleaseSyncMark(info.getPlayerId());
        }
    }


    @Mark(ServiceMark.STORE_ITEMS)
    public SuccessOrFailure requestStoreItems(int storeId, int page)
    {
        return SuccessOrFailure.buildSuccess(cPlayer.requestStoreItems(storeId, page));
    }


    @Mark(ServiceMark.STORE_BUY_ITEM)
    public SuccessOrFailure buyStoreItem(long itemId, Channel channel)
    {
        AuthenticatedInfo info = channel.attr(AuthenticateHandler.AUTHORIZATION_KEY).get();

        if (!syncBuyItem.acquireSyncMark(info.getPlayerId()))
        {
            return SuccessOrFailure.FAIL;
        }

        try
        {
            ResultBuyItem result = cPlayer.buyStoreItem(itemId, info.getPlayerId(), channel);

            if (result.isSuccess())
            {
                return SuccessOrFailure.buildSuccess(new SList<>(result.getChangedMaterials()));
            }

            return SuccessOrFailure.FAIL;
        }
        finally
        {
            syncBuyItem.deferredReleaseSyncMark(info.getPlayerId());
        }
    }

    @Mark(ServiceMark.TALENT_QUERY)
    public SuccessOrFailure requestTalent(byte talentCategoryId, AuthenticatedInfo info)
    {
        return SuccessOrFailure.buildSuccess(new SPrimitive<>(cPlayer.requestTalent(talentCategoryId, info)));
    }


    @Mark(ServiceMark.LEARNING_TALENTS)
    public SuccessOrFailure learnTalents(long learnedTalents, AuthenticatedInfo info)
    {
        return SuccessOrFailure.build(cPlayer.learnTalents(learnedTalents, info));
    }


    @Mark(ServiceMark.DECOMPOSE_ITEM)
    public SuccessOrFailure decomposeEquipment(int itemGuid, long itemId, Channel channel)
    {
        AuthenticatedInfo authenticatedInfo = channel.attr(AuthenticateHandler.AUTHORIZATION_KEY).get();

        ResultDecomposeGear result = cPlayer.decomposeEquipment(itemGuid, itemId, authenticatedInfo);

        if (result.isSuccess())
        {
            CChannels.writeSpec(channel, ServiceMark.REWARDS_NOTIFY, result.getDecomposeRewards());
            CChannels.writeSpec(channel, ServiceMark.MATERIALS_CHANGE, result.getChangedMaterials());

            return SuccessOrFailure.SUCCESS;
        }

        return SuccessOrFailure.FAIL;
    }


    @Mark(ServiceMark.UPGRADE_WEAPON)
    public SuccessOrFailure upgradeWeapon(long weaponId, AuthenticatedInfo info)
    {
        return SuccessOrFailure.buildSuccess(cPlayer.upgradeWeapon(weaponId, info));
    }


    @Mark(ServiceMark.UPGRADE_MODULE)
    public SuccessOrFailure upgradeModule(long moduleId, AuthenticatedInfo info)
    {
        return SuccessOrFailure.buildSuccess(cPlayer.upgradeModule(moduleId, info));
    }

}
