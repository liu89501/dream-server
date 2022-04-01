package com.dream.server.param;

import lombok.Data;

import java.util.List;

@Data
public class ResultBuyItem
{
    private boolean success;

    private List<PPlayerMaterial> changedMaterials;
}
