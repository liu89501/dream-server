package com.dream.server.config;

import com.dream.server.database.model.PlayerWeapon;
import com.dream.server.database.model.PlayerModule;

import java.util.List;

public interface Constant
{
    int DEFAULT_EQUIPMENT_NUM = 54;

    int TASK_MAX_QUERY_NUM = 24;

    int TASK_MAX_TRACKING = 4;

    int STORE_MAX_QUERY_NUM = 40;

    String DEFAULT_SKIN = "/Game/Main/Asset/Skin4";

    /** read only */
    List<PlayerWeapon> DEFAULT_WEAPONS = List.of(
            new PlayerWeapon(1179653, true, 0, 4000.f, 0.f, 0.5f, 0.1f, 0.f, 0.f, 0.f, 0.f),
            new PlayerWeapon(1310726, true, 1, 29800.f, 0.f, 1.2f, 0.2f, 0.f, 0.f, 0.f, 0.f)
    );

    /** read only */
    List<PlayerModule> DEFAULT_MODULES = List.of(
            new PlayerModule(2293762, true, 0, 200.f, 5000.f, 0.f, 0.f, 0.f, 0.f, 0.f, 0.f),
            new PlayerModule(2162691, true, 1, 200.f, 2000.f, 0.f, 0.f, 0.f, 0.f, 0.f, 0.f),
            new PlayerModule(2162692, true, 2, 200.f, 2000.f, 0.f, 0.f, 0.f, 0.f, 0.f, 0.f),
            new PlayerModule(2162693, true, 3, 200.f, 2000.f, 0.f, 0.f, 0.f, 0.f, 0.f, 0.f)
    );

    String MQ_TOPIC_DIALOG = "Dialog";

}
