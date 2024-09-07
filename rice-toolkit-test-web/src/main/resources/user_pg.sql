
DROP TABLE IF EXISTS "public"."ntr_user";
CREATE TABLE "public"."ntr_user"
(
    "id"               VARCHAR(64) COLLATE "pg_catalog"."default"  NOT NULL,
    "name"             VARCHAR(256) COLLATE "pg_catalog"."default",
    "description"      VARCHAR(1024) COLLATE "pg_catalog"."default",
    "username"         VARCHAR(256) COLLATE "pg_catalog"."default",
    "password"         VARCHAR(256) COLLATE "pg_catalog"."default",
    "logic_sign"       INT4,
    "create_time"      TIMESTAMPTZ,
    "update_time"      TIMESTAMPTZ
);

ALTER TABLE "public"."ntr_user"
    ADD CONSTRAINT "PK_NTR_USER_ID" PRIMARY KEY ("id");

CREATE INDEX if NOT EXISTS "IDX_NTR_USER_NAME" ON "public"."ntr_user" USING BTREE (
    "name" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST
);

CREATE INDEX if NOT EXISTS "IDX_NTR_USER_USERNAME" ON "public"."ntr_user" USING BTREE (
   "username" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST
);

CREATE INDEX if NOT EXISTS "IDX_NTR_USER_LOGIC_SIGN" ON "public"."ntr_user" USING BTREE (
    "logic_sign" "pg_catalog"."int4_ops" ASC NULLS LAST
);