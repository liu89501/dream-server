package com.dream.server.param;

import lombok.Data;

import java.util.List;

@Data
public class ResultDecomposeGear
{
    private boolean success;

    private List<PPlayerMaterial> changedMaterials;

    private PItemList decomposeRewards;
}
