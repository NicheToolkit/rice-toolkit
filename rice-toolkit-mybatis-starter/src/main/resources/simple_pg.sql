/*
 Navicat Premium Data Transfer

 Source Server         : postgres161
 Source Server Type    : PostgreSQL
 Source Server Version : 100010
 Source Host           : localhost:5432
 Source Catalog        : cy_tk_cf_db (cyan tool kit chief database)
 Source Schema         : public

 Target Server Type    : PostgreSQL
 Target Server Version : 100010
 File Encoding         : 65001
*/

DROP TABLE IF EXISTS "public"."cy_tk_cf_simple";
CREATE TABLE "public"."cy_tk_cf_simple" (
  "id" int8 NOT NULL,
  "name" varchar(32) COLLATE "pg_catalog"."default",
  "description" varchar(128) COLLATE "pg_catalog"."default",
  "time" int8,
  "create_time" timestamp,
  "update_time" timestamp,
  CONSTRAINT "CY_TK_CF_SIMPLE_PK" PRIMARY KEY ("id")
);

CREATE INDEX "CY_TK_CF_SIMPLE_TIME_IDX" ON "public"."cy_tk_cf_simple" USING btree (
  "time" "pg_catalog"."int8_ops" ASC NULLS LAST
);