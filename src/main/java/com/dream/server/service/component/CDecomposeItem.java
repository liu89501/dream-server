package com.dream.server.service.component;

import com.dream.container.anno.Assign;
import com.dream.container.anno.Component;
import com.dream.server.param.EItemQuality;
import com.dream.server.param.PPlayerMaterial;
import com.dream.server.settings.DecomposeSettings;
import com.dream.server.utils.ItemUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component(proxy = false, instant = true)
public class CDecomposeItem
{
    @Assign
    private DecomposeSettings decomposeSettings;
    
    public List<PPlayerMaterial> decompose(int itemGuid)
    {
        int itemQuality = ItemUtils.getItemQuality(itemGuid);

        List<DecomposeSettings.DecomposeRewards> rewards = switch (itemQuality)
                {
                    case EItemQuality.Normal -> decomposeSettings.getNormal();
                    case EItemQuality.Advanced -> decomposeSettings.getAdvanced();
                    case EItemQuality.Rare -> decomposeSettings.getRare();
                    case EItemQuality.Epic -> decomposeSettings.getEpic();
                    case EItemQuality.Legendary -> decomposeSettings.getLegendary();
                    default -> null;
                };

        List<PPlayerMaterial> materials = new ArrayList<>();

        if (rewards != null)
        {
            Random random = new Random();

            for (DecomposeSettings.DecomposeRewards reward : rewards)
            {
                int num = random.nextInt(reward.minNum, reward.maxNum);
                materials.add(new PPlayerMaterial(reward.itemGuid, num));
            }
        }

        return materials;
    }
}
