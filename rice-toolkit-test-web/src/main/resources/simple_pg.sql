
DROP TABLE IF EXISTS "public"."ntr_simple";
CREATE TABLE "public"."ntr_simple" (
  "id" VARCHAR(64) COLLATE "pg_catalog"."default" NOT NULL,
  "name" VARCHAR(128) COLLATE "pg_catalog"."default",
  "description" VARCHAR(256) COLLATE "pg_catalog"."default",
  "time" TIMESTAMPTZ,
  "create_time" TIMESTAMPTZ,
  "update_time" TIMESTAMPTZ,
  CONSTRAINT "PK_NTR_CF_SIMPLE" PRIMARY KEY ("id")
);

CREATE INDEX "IDX_NTR_SIMPLE_TIME" ON "public"."ntr_simple" USING btree (
  "time" "pg_catalog"."timestamptz_ops" ASC NULLS LAST
);