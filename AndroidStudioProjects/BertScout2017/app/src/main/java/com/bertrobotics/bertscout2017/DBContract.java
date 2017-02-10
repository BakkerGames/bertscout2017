package com.bertrobotics.bertscout2017;

import android.provider.BaseColumns;

/**
 * Created by chime on 11/13/2016.
 */

public final class DBContract {

    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private DBContract() {}

    public static class TableStandInfo implements BaseColumns {

        public static final String TABLE_NAME_STAND = "stand_scouting";

        public static final String COLNAME_STAND_EVENT = "event";
        public static final String COLNAME_STAND_TEAM = "team";
        public static final String COLNAME_STAND_MATCH = "match";
        public static final String COLNAME_STAND_ALLIANCE = "alliance";
        public static final String COLNAME_STAND_SCOUT_NAME = "scout_name";

        public static final String COLNAME_STAND_AUTO_BASE_LINE = "auto_base_line";
        public static final String COLNAME_STAND_AUTO_SCORE_HIGH = "auto_score_high";
        public static final String COLNAME_STAND_AUTO_SCORE_LOW = "auto_score_low";
        public static final String COLNAME_STAND_AUTO_PLACE_GEAR = "auto_place_gear";
        public static final String COLNAME_STAND_AUTO_SCORE_GEAR = "auto_score_gear";
        public static final String COLNAME_STAND_AUTO_OPEN_HOPPER = "auto_open_hopper";

        public static final String COLNAME_STAND_TELE_SCORE_HIGH = "tele_score_high";
        public static final String COLNAME_STAND_TELE_SCORE_LOW = "tele_score_low";
        public static final String COLNAME_STAND_TELE_GEARS_RECEIVED = "tele_gears_received";
        public static final String COLNAME_STAND_TELE_GEARS_PLACED = "tele_gears_placed";
        public static final String COLNAME_STAND_TELE_CLIMBED = "tele_climbed";
        public static final String COLNAME_STAND_TELE_CLIMB_TIME = "tele_climb_time";
        public static final String COLNAME_STAND_TELE_TOUCHPAD = "tele_touchpad";
        public static final String COLNAME_STAND_TELE_HUMAN_SPEED = "tele_human_speed";
        public static final String COLNAME_STAND_TELE_PILOT_SPEED = "tele_pilot_speed";
        public static final String COLNAME_STAND_TELE_PENALTIES = "tele_penalties";

        public static final String COLNAME_STAND_COMMENT = "comment";

        public static final String SQL_QUERY_CREATE_TABLE =
                "CREATE TABLE " + TableStandInfo.TABLE_NAME_STAND + " (" +

                        TableStandInfo._ID + " INTEGER PRIMARY KEY" +
                        ", " + TableStandInfo.COLNAME_STAND_EVENT + " TEXT" +
                        ", " + TableStandInfo.COLNAME_STAND_MATCH + " TEXT" +
                        ", " + TableStandInfo.COLNAME_STAND_TEAM + " INTEGER" +
                        ", " + TableStandInfo.COLNAME_STAND_ALLIANCE + " TEXT" +
                        ", " + TableStandInfo.COLNAME_STAND_SCOUT_NAME + " TEXT" +

                        ", " + TableStandInfo.COLNAME_STAND_AUTO_BASE_LINE + " INTEGER" +
                        ", " + TableStandInfo.COLNAME_STAND_AUTO_SCORE_HIGH + " INTEGER" +
                        ", " + TableStandInfo.COLNAME_STAND_AUTO_SCORE_LOW + " INTEGER" +
                        ", " + TableStandInfo.COLNAME_STAND_AUTO_PLACE_GEAR + " INTEGER" +
                        ", " + TableStandInfo.COLNAME_STAND_AUTO_OPEN_HOPPER + " INTEGER" +

                        ", " + TableStandInfo.COLNAME_STAND_TELE_SCORE_HIGH + " INTEGER" +
                        ", " + TableStandInfo.COLNAME_STAND_TELE_SCORE_LOW + " INTEGER" +
                        ", " + TableStandInfo.COLNAME_STAND_TELE_GEARS_RECEIVED + " INTEGER" +
                        ", " + TableStandInfo.COLNAME_STAND_TELE_GEARS_PLACED + " INTEGER" +
                        ", " + TableStandInfo.COLNAME_STAND_TELE_CLIMBED + " INTEGER" +
                        ", " + TableStandInfo.COLNAME_STAND_TELE_CLIMB_TIME + " INTEGER" +
                        ", " + TableStandInfo.COLNAME_STAND_TELE_TOUCHPAD + " INTEGER" +
                        ", " + TableStandInfo.COLNAME_STAND_TELE_HUMAN_SPEED + " INTEGER" +
                        ", " + TableStandInfo.COLNAME_STAND_TELE_PILOT_SPEED + " INTEGER" +
                        ", " + TableStandInfo.COLNAME_STAND_TELE_PENALTIES + " INTEGER" +

                        ", " + TableStandInfo.COLNAME_STAND_COMMENT + " TEXT" +

                        ");";

        public static final String SQL_QUERY_DELETE_TABLE =
                "DROP TABLE IF EXISTS " + TABLE_NAME_STAND + ";";

    }

    /* Inner class that defines the table contents */
    public static class TablePitInfo implements BaseColumns {

        public static final String TABLE_NAME_PIT = "pit_info";

        public static final String COLNAME_PIT_EVENT = "event";
        public static final String COLNAME_PIT_TEAM = "team";
        public static final String COLNAME_PIT_SCOUT_NAME = "scout_name";

        public static final String COLNAME_PIT_TEAM_YEARS = "team_years";
        public static final String COLNAME_PIT_TEAM_MEMBERS = "team_members";

        public static final String COLNAME_PIT_HEIGHT = "height";
        public static final String COLNAME_PIT_WEIGHT = "weight";
        public static final String COLNAME_PIT_NUM_CIMS = "num_cims";
        public static final String COLNAME_PIT_MAX_SPEED = "max_speed";
        public static final String COLNAME_PIT_WHEEL_TYPE = "wheel_type";
        public static final String COLNAME_PIT_WHEEL_LAYOUT = "wheel_layout";
        public static final String COLNAME_PIT_MAX_FUEL = "max_fuel";
        public static final String COLNAME_PIT_SHOOT_SPEED = "shoot_speed";
        public static final String COLNAME_PIT_SHOOT_LOCATION = "shoot_location";

        public static final String COLNAME_PIT_CAN_SHOOT_HIGH = "can_shoot_high";
        public static final String COLNAME_PIT_CAN_SHOOT_LOW = "can_shoot_low";
        public static final String COLNAME_PIT_FLOOR_PICKUP = "floor_pickup";
        public static final String COLNAME_PIT_TOP_LOADER = "top_loader";
        public static final String COLNAME_PIT_AUTO_AIM = "auto_aim";
        public static final String COLNAME_PIT_CAN_CARRY_GEAR = "can_carry_gear";

        public static final String COLNAME_PIT_CAN_CLIMB = "can_climb";
        public static final String COLNAME_PIT_OWN_ROPE = "own_rope";

        public static final String COLNAME_PIT_START_LEFT = "start_left";
        public static final String COLNAME_PIT_START_CENTER = "start_center";
        public static final String COLNAME_PIT_START_RIGHT = "start_right";

        public static final String COLNAME_PIT_AUTO_NUM_MODES = "auto_num_modes";
        public static final String COLNAME_PIT_AUTO_BASE_LINE = "auto_base_line";
        public static final String COLNAME_PIT_AUTO_PLACE_GEAR = "auto_place_gear";
        public static final String COLNAME_PIT_AUTO_HIGH_GOAL = "auto_high_goal";
        public static final String COLNAME_PIT_AUTO_LOW_GOAL = "auto_low_goal";
        public static final String COLNAME_PIT_AUTO_HOPPER = "auto_hopper";

        public static final String COLNAME_PIT_TEAM_RATING = "team_rating";
        public static final String COLNAME_PIT_COMMENT = "comment";

        public static final String SQL_QUERY_CREATE_TABLE =
                "CREATE TABLE " + TablePitInfo.TABLE_NAME_PIT + " (" +

                        TablePitInfo._ID + " INTEGER PRIMARY KEY" +
                        ", " + TablePitInfo.COLNAME_PIT_EVENT + " TEXT" +
                        ", " + TablePitInfo.COLNAME_PIT_TEAM + " INTEGER" +
                        ", " + TablePitInfo.COLNAME_PIT_SCOUT_NAME + " TEXT" +

                        ", " + TablePitInfo.COLNAME_PIT_TEAM_YEARS + " INTEGER" +
                        ", " + TablePitInfo.COLNAME_PIT_TEAM_MEMBERS + " INTEGER" +

                        ", " + TablePitInfo.COLNAME_PIT_HEIGHT + " INTEGER" +
                        ", " + TablePitInfo.COLNAME_PIT_WEIGHT + " INTEGER" +
                        ", " + TablePitInfo.COLNAME_PIT_NUM_CIMS + " INTEGER" +
                        ", " + TablePitInfo.COLNAME_PIT_MAX_SPEED + " INTEGER" +
                        ", " + TablePitInfo.COLNAME_PIT_WHEEL_TYPE + " TEXT" +
                        ", " + TablePitInfo.COLNAME_PIT_WHEEL_LAYOUT + " TEXT" +
                        ", " + TablePitInfo.COLNAME_PIT_MAX_FUEL + " INTEGER" +
                        ", " + TablePitInfo.COLNAME_PIT_SHOOT_SPEED + " INTEGER" +
                        ", " + TablePitInfo.COLNAME_PIT_SHOOT_LOCATION + " TEXT" +

                        ", " + TablePitInfo.COLNAME_PIT_CAN_SHOOT_HIGH + " INTEGER" +
                        ", " + TablePitInfo.COLNAME_PIT_CAN_SHOOT_LOW + " INTEGER" +
                        ", " + TablePitInfo.COLNAME_PIT_FLOOR_PICKUP + " INTEGER" +
                        ", " + TablePitInfo.COLNAME_PIT_TOP_LOADER + " INTEGER" +
                        ", " + TablePitInfo.COLNAME_PIT_AUTO_AIM + " INTEGER" +
                        ", " + TablePitInfo.COLNAME_PIT_CAN_CARRY_GEAR + " INTEGER" +

                        ", " + TablePitInfo.COLNAME_PIT_CAN_CLIMB + " INTEGER" +
                        ", " + TablePitInfo.COLNAME_PIT_OWN_ROPE + " INTEGER" +

                        ", " + TablePitInfo.COLNAME_PIT_START_LEFT + " INTEGER" +
                        ", " + TablePitInfo.COLNAME_PIT_START_CENTER + " INTEGER" +
                        ", " + TablePitInfo.COLNAME_PIT_START_RIGHT + " INTEGER" +

                        ", " + TablePitInfo.COLNAME_PIT_AUTO_NUM_MODES + " INTEGER" +
                        ", " + TablePitInfo.COLNAME_PIT_AUTO_BASE_LINE + " INTEGER" +
                        ", " + TablePitInfo.COLNAME_PIT_AUTO_PLACE_GEAR + " INTEGER" +
                        ", " + TablePitInfo.COLNAME_PIT_AUTO_HIGH_GOAL + " INTEGER" +
                        ", " + TablePitInfo.COLNAME_PIT_AUTO_LOW_GOAL + " INTEGER" +
                        ", " + TablePitInfo.COLNAME_PIT_AUTO_HOPPER + " INTEGER" +

                        ", " + TablePitInfo.COLNAME_PIT_TEAM_RATING + " INTEGER" +

                        ", " + TablePitInfo.COLNAME_PIT_COMMENT + " TEXT" +

                        ");";

        public static final String SQL_QUERY_DELETE_TABLE =
                "DROP TABLE IF EXISTS " + TABLE_NAME_PIT + ";";

        /*
        public static final String SQL_QUERY_UPGRADE_VER_4_START_LEFT =
                "ALTER TABLE " + TABLE_NAME_PIT + " ADD COLUMN " + DBContract.TablePitInfo.COLNAME_START_LEFT + " INTEGER;";
        public static final String SQL_QUERY_UPGRADE_VER_4_START_CENTER =
                "ALTER TABLE " + TABLE_NAME_PIT + " ADD COLUMN " + DBContract.TablePitInfo.COLNAME_START_CENTER + " INTEGER;";
        public static final String SQL_QUERY_UPGRADE_VER_4_START_RIGHT =
                "ALTER TABLE " + TABLE_NAME_PIT + " ADD COLUMN " + DBContract.TablePitInfo.COLNAME_START_RIGHT + " INTEGER;";
        public static final String SQL_QUERY_UPGRADE_VER_4_HAS_AUTONOMOUS =
                "ALTER TABLE " + TABLE_NAME_PIT + " ADD COLUMN " + DBContract.TablePitInfo.COLNAME_HAS_AUTONOMOUS + " INTEGER;";
        public static final String SQL_QUERY_UPGRADE_VER_4_PIT_COMMENT =
                "ALTER TABLE " + TABLE_NAME_PIT + " ADD COLUMN " + DBContract.TablePitInfo.COLNAME_PIT_COMMENT + " TEXT;";
        */

    }

}
