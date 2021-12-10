/*
 Navicat Premium Data Transfer

 Source Server         : postgres161
 Source Server Type    : PostgreSQL
 Source Server Version : 100010
 Source Host           : localhost:5432
 Source Catalog        : ntr_simple_db (niche toolkit rice simple database)
 Source Schema         : public

 Target Server Type    : PostgreSQL
 Target Server Version : 100010
 File Encoding         : 65001
*/

DROP TABLE IF EXISTS "public"."ntr_simple";
CREATE TABLE "public"."ntr_simple" (
  "id" int8 NOT NULL,
  "name" varchar(32) COLLATE "pg_catalog"."default",
  "description" varchar(128) COLLATE "pg_catalog"."default",
  "time" int8,
  "create_time" timestamp,
  "update_time" timestamp,
  CONSTRAINT "PK_NTR_CF_SIMPLE" PRIMARY KEY ("id")
);

CREATE INDEX "IDX_NTR_SIMPLE_TIME" ON "public"."ntr_simple" USING btree (
  "time" "pg_catalog"."int8_ops" ASC NULLS LAST
);