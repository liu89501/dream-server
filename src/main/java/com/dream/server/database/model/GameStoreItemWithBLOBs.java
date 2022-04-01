package com.dream.server.database.model;


import com.dream.server.param.PItem;
import com.dream.server.param.PAcquisitionCostList;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * game_store_item
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class GameStoreItemWithBLOBs extends GameStoreItem {

    private PItem itemData;

    private PAcquisitionCostList itemCostData;
}