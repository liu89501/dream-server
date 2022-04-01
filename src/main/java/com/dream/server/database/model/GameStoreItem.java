package com.dream.server.database.model;

import java.io.Serializable;
import lombok.Data;

/**
 * game_store_item
 */
@Data
public class GameStoreItem implements Serializable {

    private Long gsiId;

    private Integer storeId;
}