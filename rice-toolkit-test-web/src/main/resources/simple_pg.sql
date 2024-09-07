
DROP TABLE IF EXISTS "public"."ntr_simple";
CREATE TABLE "public"."ntr_simple" (
  "id" VARCHAR(64) COLLATE "pg_catalog"."default" NOT NULL,
  "name" VARCHAR(128) COLLATE "pg_catalog"."default",
  "description" VARCHAR(256) COLLATE "pg_catalog"."default",
  "time" TIMESTAMPTZ,
  "logic_sign"       INT4,
  "create_time" TIMESTAMPTZ,
  "update_time" TIMESTAMPTZ
);

ALTER TABLE "public"."ntr_simple"
    ADD CONSTRAINT "PK_NTR_SIMPLE_ID" PRIMARY KEY ("id");

CREATE INDEX "IDX_NTR_SIMPLE_TIME" ON "public"."ntr_simple" USING btree (
  "time" "pg_catalog"."timestamptz_ops" ASC NULLS LAST
);

CREATE INDEX if NOT EXISTS "IDX_NTR_SIMPLE_LOGIC_SIGN" ON "public"."ntr_simple" USING BTREE (
   "logic_sign" "pg_catalog"."int4_ops" ASC NULLS LAST
);