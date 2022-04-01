package com.dream.server.database.model;

import java.io.Serializable;
import lombok.Data;

/**
 * template_item
 * @author 
 */
@Data
public class TemplateItem implements Serializable {
    private Integer itemGuid;

    private Byte itemQuality;

    private static final long serialVersionUID = 1L;
}