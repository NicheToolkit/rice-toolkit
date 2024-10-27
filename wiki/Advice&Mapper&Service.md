# Advice

the advice is used to handle the models or entities on service.
<table style="text-align: center;">
<tr>
<th>name</th>
<th>typeParams</th>
<th>methods</th>
<th>description</th>
</tr>
<tr>
<td rowspan=4 style="vertical-align: middle;"><code>AlertAdvice</code></td>
<td rowspan=4 style="vertical-align: middle;"><code>&lt;I&gt;</code></td>
<td><code>beforeAlert(I id)</code></td>
<td rowspan=4 style="vertical-align: middle;">the advice is used to handle <code>alert</code> and <code>alertAll</code> methods.</td>
</tr>
<tr>
<td><code>beforeAlertAll(Collection&lt;I&gt; idList)</code></td>
</tr>
<tr>
<td><code>afterAlert(I id)</code></td>
</tr>
<tr>
<td><code>afterAlertAll(Collection&lt;I&gt; idList)</code></td>
</tr>
<tr>
<td rowspan=4 style="vertical-align: middle;"><code>DeleteAdvice</code></td>
<td rowspan=4 style="vertical-align: middle;"><code>&lt;E extends RestId&lt;I&gt;,I&gt;</code></td>
<td><code>beforeDelete(E entity)</code></td>
<td rowspan=4 style="vertical-align: middle;">the advice is used to handle <code>delete</code> and <code>deleteAll</code> methods.</td>
</tr>
<tr>
<td><code>beforeDeleteAll(Collection&lt;E&gt; entityList)</code></td>
</tr>
<tr>
<td><code>afterDelete(E entity)</code></td>
</tr>
<tr>
<td><code>afterDeleteAll(Collection&lt;E&gt; entityList)</code></td>
</tr>
<tr>
<td rowspan=4 style="vertical-align: middle;"><code>OperateAdvice</code></td>
<td rowspan=4 style="vertical-align: middle;"><code>&lt;E extends RestId&lt;I&gt;,I&gt;</code></td>
<td><code>beforeOperate(E entity)</code></td>
<td rowspan=4 style="vertical-align: middle;">the advice is used to handle <code>operate</code> and <code>operateAll</code> methods.</td>
</tr>
<tr>
<td><code>beforeOperateAll(Collection&lt;E&gt; entityList)</code></td>
</tr>
<tr>
<td><code>afterOperate(E entity)</code></td>
</tr>
<tr>
<td><code>afterOperateAll(Collection&lt;E&gt; entityList)</code></td>
</tr>
<tr>
<td rowspan=4 style="vertical-align: middle;"><code>RemoveAdvice</code></td>
<td rowspan=4 style="vertical-align: middle;"><code>&lt;E extends RestId&lt;I&gt;,I&gt;</code></td>
<td><code>beforeRemove(E entity)</code></td>
<td rowspan=4 style="vertical-align: middle;">the advice is used to handle <code>remove</code> and <code>removeAll</code> methods.</td>
</tr>
<tr>
<td><code>beforeRemoveAll(Collection&lt;E&gt; entityList)</code></td>
</tr>
<tr>
<td><code>afterRemove(E entity)</code></td>
</tr>
<tr>
<td><code>afterRemoveAll(Collection&lt;E&gt; entityList)</code></td>
</tr>
<tr>
<td rowspan=8 style="vertical-align: middle;"><code>SaveAdvice</code></td>
<td rowspan=8 style="vertical-align: middle;"><code>&lt;M extends RestId&lt;I&gt;,I&gt;</code></td>
<td><code>beforeCreate(M model)</code></td>
<td rowspan=8 style="vertical-align: middle;">the advice is used to handle <code>create</code> 、<code>update</code> 、<code>save</code> and <code>saveAll</code> methods.</td>
</tr>
<tr>
<td><code>beforeUpdate(M model)</code></td>
</tr>
<tr>
<td><code>beforeSave(M model)</code></td>
</tr>
<tr>
<td><code>beforeSaveAll(Collection&lt;M&gt; modelList)</code></td>
</tr>
<tr>
<td><code>afterCreate(M model)</code></td>
</tr>
<tr>
<td><code>afterUpdate(M model)</code></td>
</tr>
<tr>
<td><code>afterSave(M model)</code></td>
</tr>
<tr>
<td><code>afterSaveAll(Collection&lt;M&gt; modelList)</code></td>
</tr>
<tr>
<td rowspan=4 style="vertical-align: middle;"><code>BuilderAdvice</code></td>
<td rowspan=4 style="vertical-align: middle;"><code>&lt;M extends RestId&lt;I&gt;, E extends RestId&lt;I&gt;,I&gt;</code></td>
<td><code>buildEntity(M model, E entity, Object... idArray)</code></td>
<td rowspan=4 style="vertical-align: middle;">the advice is used to handle <code>buildEntity</code> 、<code>buildEntityList</code> 、<code>buildModel</code> and <code>buildModelList</code> methods.</td>
</tr>
<tr>
<td><code>buildEntityList(Collection&lt;M&gt; modelList, List&lt;E&gt; entityList, Object... idArray)</code></td>
</tr>
<tr>
<td><code>buildModel(E entity, M model, Boolean... isLoadArray)</code></td>
</tr>
<tr>
<td><code>buildModelList(Collection&lt;E&gt; entityList, List&lt;M&gt; modelList, Boolean... isLoadArray)</code></td>
</tr>
<tr>
<td rowspan=4 style="vertical-align: middle;"><code>MutateAdvice</code></td>
<td rowspan=4 style="vertical-align: middle;"><code>&lt;M extends RestId&lt;I&gt;, E extends RestId&lt;I&gt;,I&gt;</code></td>
<td><code>mutateEntity(E entity, Boolean... isLoadArray)</code></td>
<td rowspan=4 style="vertical-align: middle;">the advice is used to handle <code>mutateEntity</code> 、<code>mutateEntityList</code> 、<code>mutateModel</code> and <code>mutateModelList</code> methods.</td>
</tr>
<tr>
<td><code>mutateEntityList(List&lt;E&gt; entityList, ConsumerActuator&lt;E&gt; actuator, Boolean... isLoadArray)</code></td>
</tr>
<tr>
<td><code>mutateModel(M model, Object... idArray)</code></td>
</tr>
<tr>
<td><code>mutateModelList(List&lt;M&gt; modelList, ConsumerActuator&lt;M&gt; actuator, Object... idArray)</code></td>
</tr>
<tr>
<td rowspan=3 style="vertical-align: middle;"><code>TablenameAdvice</code></td>
<td rowspan=3 style="vertical-align: middle;"><code>&lt;M extends RestId&lt;I&gt;, I, K&gt;</code></td>
<td><code>resolveTablename(K tablekey)</code></td>
<td rowspan=3 style="vertical-align: middle;">the advice is used to handle <code>resolveTablename</code> method.</td>
</tr>
<tr>
<td><code>resolveTablename(K tablekey, M model)</code></td>
</tr>
<tr>
<td><code>resolveTablename(K tablekey, Collection&lt;M&gt; modelList)</code></td>
</tr>
<tr>
<td rowspan=8 style="vertical-align: middle;"><code>FilterAdvice</code></td>
<td rowspan=8 style="vertical-align: middle;"><code>&lt;F extends IdFilter&lt;I, K&gt;, I, K&gt;</code></td>
<td><code>queryWhereSql(F filter)</code></td>
<td rowspan=8 style="vertical-align: middle;">the advice is used to handle filter build <code>SQL</code>methods.</td>
</tr>
<tr>
<td><code>deleteWhereSql(F filter)</code></td>
</tr>
<tr>
<td><code>removeWhereSql(F filter)</code></td>
</tr>
<tr>
<td><code>operateWhereSql(F filter)</code></td>
</tr>
<tr>
<td><code>findLoadArray(F filter)</code></td>
</tr>
<tr>
<td><code>queryLoadArray(F filter)</code></td>
</tr>
<tr>
<td><code>fieldArray(F filter)</code></td>
</tr>
<tr>
<td><code>tablekey(F filter)</code></td>
</tr>
<tr>
</table>
* examples

```java

@Service
public class SimpleServiceImpl extends RestInfoService<SimpleModel, SimpleEntity, SimpleFilter> implements SimpleService {

    @Override
    protected void optionalInit(@NonNull SimpleModel model) throws RestException {
        model.setTime(Optional.ofNullable(model.getTime()).orElse(new Date()));
    }

    @Override
    public String queryWhereSql(SimpleFilter filter) throws RestException {
        return filter.toTimeSql("create_time").toNameSql("name").toQuerySql(this, "logic").addSorts("id").toIdSql().toSql();
    }
}
```

# Mapper

* default mapper

<table style="text-align: center;">
<tr>
<th>name</th>
<th>typeParams</th>
<th>methods</th>
<th>description</th>
</tr>
<tr>
<td rowspan=4 style="vertical-align: middle;"><code>AlertMapper</code></td>
<td rowspan=4 style="vertical-align: middle;"><code>&lt;S,I&gt;</code></td>
<td><code>alertById(@Param("id") I id, @Param("status") S status)</code></td>
<td rowspan=4 style="vertical-align: middle;">the mapper is used to handle <code>alert</code> and <code>alertAll</code> methods.</td>
</tr>
<tr>
<td><code>alertDynamicById(@Param("tablename") String tablename, @Param("id") I id, @Param("status") S status)</code></td>
</tr>
<tr>
<td><code>alertAll(@Param("idList") Collection&lt;I&gt; idList, @Param("status") S status)</code></td>
</tr>
<tr>
<td><code>alertDynamicAll(@Param("tablename") String tablename, @Param("idList") Collection&lt;I&gt; idList, @Param("status") S status)</code></td>
</tr>
<tr>
<td rowspan=6 style="vertical-align: middle;"><code>DeleteMapper</code></td>
<td rowspan=6 style="vertical-align: middle;"><code>&lt;I&gt;</code></td>
<td><code>deleteById(@Param("id") I id)</code></td>
<td rowspan=6 style="vertical-align: middle;">the mapper is used to handle <code>delete</code> and <code>deleteAll</code> methods.</td>
</tr>
<tr>
<td><code>deleteDynamicById(@Param("tablename") String tablename, @Param("id") I id)</code></td>
</tr>
<tr>
<td><code>deleteAll(@Param("idList") Collection&lt;I&gt; idList)</code></td>
</tr>
<tr>
<td><code>deleteDynamicAll(@Param("tablename") String tablename, @Param("idList") Collection&lt;I&gt; idList)</code></td>
</tr>
<tr>
<td><code>deleteAllByWhere(@Param("whereSql") String whereSql)</code></td>
</tr>
<tr>
<td><code>deleteDynamicAllByWhere(@Param("tablename") String tablename, @Param("whereSql") String whereSql)</code></td>
</tr>
<tr>
<td rowspan=6 style="vertical-align: middle;"><code>OperateMapper</code></td>
<td rowspan=6 style="vertical-align: middle;"><code>&lt;I&gt;</code></td>
<td><code>operateById(@Param("id") I id, @Param("operate") Integer operate)</code></td>
<td rowspan=6 style="vertical-align: middle;">the mapper is used to handle <code>operate</code> and <code>operateAll</code> methods.</td>
</tr>
<tr>
<td><code>operateDynamicById(@Param("tablename") String tablename, @Param("id") I id, @Param("operate") Integer operate)</code></td>
</tr>
<tr>
<td><code>operateAll(@Param("idList") Collection&lt;I&gt; idList, @Param("operate") Integer operate)</code></td>
</tr>
<tr>
<td><code>operateDynamicAll(@Param("tablename") String tablename, @Param("idList") Collection&lt;I&gt; idList, @Param("operate") Integer operate)</code></td>
</tr>
<tr>
<td><code>operateAllByWhere(@Param("whereSql") String whereSql, @Param("operate") Integer operate)</code></td>
</tr>
<tr>
<td><code>operateDynamicAllByWhere(@Param("tablename") String tablename, @Param("whereSql") String whereSql, @Param("operate") Integer operate)</code></td>
</tr>
<tr>
<td rowspan=6 style="vertical-align: middle;"><code>RemoveMapper</code></td>
<td rowspan=6 style="vertical-align: middle;"><code>&lt;I&gt;</code></td>
<td><code>removeById(@Param("id") I id, @Param("logic") String logic)</code></td>
<td rowspan=6 style="vertical-align: middle;">the mapper is used to handle <code>remove</code> and <code>removeAll</code> methods.</td>
</tr>
<tr>
<td><code>removeDynamicById(@Param("tablename") String tablename, @Param("id") I id, @Param("logic") String logic)</code></td>
</tr>
<tr>
<td><code>removeAll(@Param("idList") Collection&lt;I&gt; idList, @Param("logic") String logic)</code></td>
</tr>
<tr>
<td><code>removeDynamicAll(@Param("tablename") String tablename, @Param("idList") Collection&lt;I&gt; idList, @Param("logic") String logic)</code></td>
</tr>
<tr>
<td><code>removeAllByWhere(@Param("whereSql") String whereSql, @Param("logic") String logic)</code></td>
</tr>
<tr>
<td><code>removeDynamicAllByWhere(@Param("tablename") String tablename, @Param("whereSql") String whereSql,@Param("logic") String logic)</code></td>
</tr>
<tr>
<td rowspan=4 style="vertical-align: middle;"><code>SaveMapper</code></td>
<td rowspan=4 style="vertical-align: middle;"><code>&lt;E extends RestId&lt;I&gt;,I&gt;</code></td>
<td><code>save(@Param("entity") E entity)</code></td>
<td rowspan=4 style="vertical-align: middle;">the mapper is used to handle <code>save</code> and <code>saveAll</code> methods.</td>
</tr>
<tr>
<td><code>saveDynamic(@Param("tablename") String tablename, @Param("entity") E entity)</code></td>
</tr>
<tr>
<td><code>saveAll(@Param("entityList") Collection&lt;E&gt; entityList)</code></td>
</tr>
<tr>
<td><code>saveDynamicAll(@Param("tablename") String tablename, @Param("entityList") Collection&lt;E&gt; entityList)</code></td>
</tr>
<tr>
<td rowspan=6 style="vertical-align: middle;"><code>FindMapper</code></td>
<td rowspan=6 style="vertical-align: middle;"><code>&lt;E extends RestId&lt;I&gt;,I&gt;</code></td>
<td><code>findById(@Param("id") I id)</code></td>
<td rowspan=6 style="vertical-align: middle;">the mapper is used to handle <code>find</code> and <code>findAll</code> methods.</td>
</tr>
<tr>
<td><code>findDynamicById(@Param("tablename") String tablename, @Param("id") I id)</code></td>
</tr>
<tr>
<td><code>findAll(@Param("idList") Collection&lt;I&gt; idList)</code></td>
</tr>
<tr>
<td><code>findDynamicAll(@Param("tablename") String tablename, @Param("idList") Collection&lt;I&gt; idList)</code></td>
</tr>
<tr>
<td><code>findAllByWhere(@Param("whereSql") String whereSql)</code></td>
</tr>
<tr>
<td><code>findDynamicAllByWhere(@Param("tablename") String tablename, @Param("whereSql") String whereSql)</code></td>
</tr>
<tr>
<td style="vertical-align: middle;"><code>SuperMapper</code></td>
<td style="vertical-align: middle;"><code>&lt;E extends RestId&lt;I&gt;,I&gt;</code></td>
<td></td>
<td style="vertical-align: middle;">the mapper is default super mapper.</td>
</tr>
<tr>
<td rowspan=8 style="vertical-align: middle;"><code>InfoMapper</code></td>
<td rowspan=8 style="vertical-align: middle;"><code>&lt;E extends RestId&lt;I&gt;,I&gt;</code></td>
<td><code>findByName(@Param("name") String name, @Param("logic") String logic)</code></td>
<td rowspan=8 style="vertical-align: middle;">the mapper is used to handle <code>find</code> and <code>findAll</code> methods.</td>
</tr>
<tr>
<td><code>findDynamicByName(@Param("tablename") String tablename, @Param("name") String name, @Param("logic") String logic)</code></td>
</tr>
<tr>
<td><code>findByNameAndNotId(@Param("name") String name, @Param("id") I id, @Param("logic") String logic)</code></td>
</tr>
<tr>
<td><code>findDynamicByNameAndNotId(@Param("tablename") String tablename, @Param("name") String name, @Param("id") I id, @Param("logic") String logic)</code></td>
</tr>
<tr>
<td><code>findByEntity(@Param("entity") E entity, @Param("logic") String logic)</code></td>
</tr>
<tr>
<td><code>findDynamicByEntity(@Param("tablename") String tablename, @Param("entity") E entity, @Param("logic") String logic)</code></td>
</tr>
<tr>
<td><code>findByEntityAndNotId(@Param("entity") E entity, @Param("id") I id, @Param("logic") String logic)</code></td>
</tr>
<tr>
<td><code>findDynamicByEntityAndNotId(@Param("tablename") String tablename, @Param("entity") E entity, @Param("id") I id, @Param("logic") String logic)</code></td>
</tr>
</table>


* link mapper

<table style="text-align: center;">
<tr>
<th>name</th>
<th>typeParams</th>
<th>methods</th>
<th>description</th>
</tr>
<tr>
<td rowspan=4 style="vertical-align: middle;"><code>DeleteLinkMapper</code></td>
<td rowspan=4 style="vertical-align: middle;"><code>&lt;L, I&gt;</code></td>
<td><code>deleteByLinkId(@Param("linkId") L linkId)</code></td>
<td rowspan=4 style="vertical-align: middle;">the mapper is used to handle <code>deleteByLinkId</code> and <code>deleteAllByLinkIds</code> methods.</td>
</tr>
<tr>
<td><code>deleteDynamicByLinkId(@Param("tablename") String tablename, @Param("linkId") L linkId)</code></td>
</tr>
<tr>
<td><code>deleteAllByLinkIds(@Param("linkIdList") Collection&lt;L&gt; linkIdList)</code></td>
</tr>
<tr>
<td><code>deleteDynamicAllByLinkIds(@Param("tablename") String tablename, @Param("linkIdList") Collection&lt;L&gt; linkIdList)</code></td>
</tr>
<tr>
<td rowspan=4 style="vertical-align: middle;"><code>OperateLinkMapper</code></td>
<td rowspan=4 style="vertical-align: middle;"><code>&lt;L, I&gt;</code></td>
<td><code>operateByLinkId(@Param("linkId") L linkId, @Param("operate") Integer operate)</code></td>
<td rowspan=4 style="vertical-align: middle;">the mapper is used to handle <code>operateByLinkId</code> and <code>operateAllByLinkIds</code> methods.</td>
</tr>
<tr>
<td><code>operateDynamicByLinkId(@Param("tablename") String tablename, @Param("linkId") L linkId, @Param("operate") Integer operate)</code></td>
</tr>
<tr>
<td><code>operateAllByLinkIds(@Param("linkIdList") Collection&lt;L&gt; linkIdList, @Param("operate") Integer operate)</code></td>
</tr>
<tr>
<td><code>operateDynamicAllByLinkIds(@Param("tablename") String tablename, @Param("linkIdList") Collection&lt;L&gt; linkIdList, @Param("operate") Integer operate)</code></td>
</tr>
<tr>
<td rowspan=4 style="vertical-align: middle;"><code>RemoveLinkMapper</code></td>
<td rowspan=4 style="vertical-align: middle;"><code>&lt;L, I&gt;</code></td>
<td><code>removeByLinkId(@Param("linkId") L linkId, @Param("logic") String logic)</code></td>
<td rowspan=4 style="vertical-align: middle;">the mapper is used to handle <code>removeByLinkId</code> and <code>removeAllByLinkIds</code> methods.</td>
</tr>
<tr>
<td><code>removeDynamicByLinkId(@Param("tablename") String tablename, @Param("linkId") L linkId, @Param("logic") String logic)</code></td>
</tr>
<tr>
<td><code>removeAllByLinkIds(@Param("linkIdList") Collection&lt;L&gt; linkIdList, @Param("logic") String logic)</code></td>
</tr>
<tr>
<td><code>removeDynamicAllByLinkIds(@Param("tablename") String tablename, @Param("linkIdList") Collection&lt;L&gt; linkIdList, @Param("logic") String logic)</code></td>
</tr>
<tr>
<td rowspan=4 style="vertical-align: middle;"><code>AlertLinkMapper</code></td>
<td rowspan=4 style="vertical-align: middle;"><code>&lt;L, S, I&gt;</code></td>
<td><code>alertByLinkId(@Param("linkId") L linkId, @Param("status") S status)</code></td>
<td rowspan=4 style="vertical-align: middle;">the mapper is used to handle <code>alertByLinkId</code> and <code>alertByLinkIds</code> methods.</td>
</tr>
<tr>
<td><code>alertDynamicByLinkId(@Param("tablename") String tablename, @Param("linkId") L linkId,  @Param("status") S status)</code></td>
</tr>
<tr>
<td><code>alertAllByLinkIds(@Param("linkIdList") Collection&lt;L&gt; linkIdList, @Param("status") S status)</code></td>
</tr>
<tr>
<td><code>alertDynamicAllByLinkIds(@Param("tablename") String tablename, @Param("linkIdList") Collection&lt;L&gt; linkIdList, @Param("status") S status)</code></td>
</tr>
</table>


* native mapper

<table style="text-align: center;">
<tr>
<th>name</th>
<th>typeParams</th>
<th>methods</th>
<th>description</th>
</tr>
<tr>
<td rowspan=4 style="vertical-align: middle;"><code>FindLoadMapper</code></td>
<td rowspan=4 style="vertical-align: middle;"><code>&lt;E extends RestId&lt;I&gt;, I&gt;</code></td>
<td><code>findByIdLoad(@Param("id") I id, @Param("loadParams") Boolean... loadParams)</code></td>
<td rowspan=4 style="vertical-align: middle;">the mapper is used to handle <code>findByIdLoad</code> and <code>findAllLoad</code> methods.</td>
</tr>
<tr>
<td><code>findDynamicByIdLoad(@Param("tablename") String tablename, @Param("id") I id, @Param("loadParams") Boolean... loadParams)</code></td>
</tr>
<tr>
<td><code>findAllLoad(@Param("idList") Collection&lt;I&gt; idList, @Param("loadParams") Boolean... loadParams)</code></td>
</tr>
<tr>
<td><code>findDynamicAllLoad(@Param("tablename") String tablename, @Param("idList") Collection&lt;I&gt; idList, @Param("loadParams") Boolean... loadParams)</code></td>
</tr>
<tr>
<td rowspan=2 style="vertical-align: middle;"><code>FilterLoadMapper</code></td>
<td rowspan=2 style="vertical-align: middle;"><code>&lt;E extends RestId&lt;I&gt;, I&gt;</code></td>
<td><code>findAllByLoadWhere(@Param("whereSql") String whereSql, @Param("loadParams") Boolean... loadParams)</code></td>
<td rowspan=2 style="vertical-align: middle;">the mapper is used to handle <code>findAllByLoadWhere</code> methods.</td>
</tr>
<tr>
<td><code>findDynamicAllByLoadWhere(@Param("tablename") String tablename, @Param("whereSql") String whereSql, @Param("loadParams") Boolean... loadParams)</code></td>
</tr>
<tr>
<td rowspan=4 style="vertical-align: middle;"><code>LinkLoadMapper</code></td>
<td rowspan=4 style="vertical-align: middle;"><code>&lt;E extends RestId&lt;I&gt;, L, I&gt;</code></td>
<td><code>findByLinkIdLoad(@Param("linkId") L linkId, @Param("loadParams") Boolean... loadParams)</code></td>
<td rowspan=4 style="vertical-align: middle;">the mapper is used to handle <code>findByLinkIdLoad</code> and <code>findByLinkIdsLoad</code>  methods.</td>
</tr>  
<tr>
<td><code>findDynamicByLinkIdLoad(@Param("tablename") String tablename, @Param("linkId") L linkId, @Param("loadParams") Boolean... loadParams)</code></td>
</tr>
<tr>
<td><code>findAllByLinkIdsLoad(@Param("linkIdList") Collection&lt;L&gt; linkIdList, @Param("loadParams") Boolean... loadParams)</code></td>
</tr>
<tr>
<td><code>findDynamicAllByLinkIdsLoad(@Param("tablename") String tablename, @Param("linkIdList") Collection&lt;L&gt; linkIdList, @Param("loadParams") Boolean... loadParams)</code></td>
</tr>
<tr>
<td rowspan=2 style="vertical-align: middle;"><code>NameLoadMapper</code></td>
<td rowspan=2 style="vertical-align: middle;"><code>&lt;E extends RestId&lt;I&gt;, I&gt;</code></td>
<td><code>findByNameLoad(@Param("name") String name,@Param("logic") String logic, @Param("loadParams") Boolean... loadParams)</code></td>
<td rowspan=2 style="vertical-align: middle;">the mapper is used to handle <code>findByNameLoad</code> methods.</td>
</tr>
<tr>
<td><code>findDynamicByNameLoad(@Param("tablename") String tablename, @Param("name") String name, @Param("logic") String logic, @Param("loadParams") Boolean... loadParams)</code></td>
</tr>
<tr>
<td rowspan=2 style="vertical-align: middle;"><code>FindFilterMapper</code></td>
<td rowspan=2 style="vertical-align: middle;"><code>&lt;E extends RestId&lt;I&lt;, F extends IdFilter&lt;I, K&lt;, I, K&lt;</code></td>
<td><code>findAllByFilterWhere(@Param("whereSql") String whereSql, @Param("filter") F filter)</code></td>
<td rowspan=2 style="vertical-align: middle;">the mapper is used to handle <code>findAllByFilterWhere</code> methods.</td>
</tr>
<tr>
<td><code>findDynamicAllByFilterWhere(@Param("tablename") String tablename, @Param("whereSql") String whereSql, @Param("filter") F filter)</code></td>
</tr>
<tr>
<td rowspan=2 style="vertical-align: middle;"><code>DeleteFilterMapper</code></td>
<td rowspan=2 style="vertical-align: middle;"><code>&lt;E extends RestId&lt;I&lt;, F extends IdFilter&lt;I, K&lt;, I, K&lt;</code></td>
<td><code>deleteAllByFilterWhere(@Param("whereSql") String whereSql, @Param("filter") F filter)</code></td>
<td rowspan=2 style="vertical-align: middle;">the mapper is used to handle <code>deleteAllByFilterWhere</code> methods.</td>
</tr>
<tr>
<td><code>deleteDynamicAllByFilterWhere(@Param("tablename") String tablename, @Param("whereSql") String whereSql, @Param("filter") F filter)</code></td>
</tr>
<tr>
<td rowspan=2 style="vertical-align: middle;"><code>RemoveFilterMapper</code></td>
<td rowspan=2 style="vertical-align: middle;"><code>&lt;E extends RestId&lt;I&lt;, F extends IdFilter&lt;I, K&lt;, I, K&lt;</code></td>
<td><code>removeAllByFilterWhere(@Param("whereSql") String whereSql, @Param("filter") F filter, @Param("logic") String logic)</code></td>
<td rowspan=2 style="vertical-align: middle;">the mapper is used to handle <code>removeAllByFilterWhere</code> methods.</td>
</tr>
<tr>
<td><code>removeDynamicAllByFilterWhere(@Param("tablename") String tablename, @Param("whereSql") String whereSql, @Param("filter") F filter, @Param("logic") String logic)</code></td>
</tr>
<tr>
<td rowspan=2 style="vertical-align: middle;"><code>OperateFilterMapper</code></td>
<td rowspan=2 style="vertical-align: middle;"><code>&lt;E extends RestId&lt;I&lt;, F extends IdFilter&lt;I, K&lt;, I, K&lt;</code></td>
<td><code>operateAllByFilterWhere(@Param("whereSql") String whereSql, @Param("filter") F filter, @Param("operate") Integer operate)</code></td>
<td rowspan=2 style="vertical-align: middle;">the mapper is used to handle <code>operateAllByFilterWhere</code> methods.</td>
</tr>
<tr>
<td><code>operateDynamicAllByFilterWhere(@Param("tablename") String tablename, @Param("whereSql") String whereSql, @Param("filter") F filter, @Param("operate") Integer operate)</code></td>
</tr>
<tr>
<td rowspan=2 style="vertical-align: middle;"><code>AlertFilterMapper</code></td>
<td rowspan=2 style="vertical-align: middle;"><code>&lt;E extends RestId&lt;I&lt;, F extends IdFilter&lt;I, K&lt;, S, I, K&lt;</code></td>
<td><code>alertAllByFilterWhere(@Param("whereSql") String whereSql, @Param("filter") F filter, @Param("status") S status)</code></td>
<td rowspan=2 style="vertical-align: middle;">the mapper is used to handle <code>alertAllByFilterWhere</code> methods.</td>
</tr>
<tr>
<td><code>alertDynamicAllByFilterWhere(@Param("tablename") String tablename, @Param("whereSql") String whereSql, @Param("filter") F filter, @Param("status") S status)</code></td>
</tr>
<tr>
<td rowspan=2 style="vertical-align: middle;"><code>FindFieldMapper</code></td>
<td rowspan=2 style="vertical-align: middle;"><code>&lt;E extends RestId&lt;I&gt;, I&gt;</code></td>
<td><code>findAllByFieldWhere(@Param("whereSql") String whereSql, @Param("fieldParams") String... fieldParams)</code></td>
<td rowspan=2 style="vertical-align: middle;">the mapper is used to handle <code>findAllByFieldWhere</code> methods.</td>
</tr>
<tr>
<td><code>findDynamicAllByFieldWhere(@Param("tablename") String tablename, @Param("whereSql") String whereSql, @Param("fieldParams") String... fieldParams)</code></td>
</tr>
</table>


* examples

```java

@Component
public interface SimpleMapper extends RestInfoMapper<SimpleEntity>, Mapper<SimpleEntity, String> {
}
```

# Service

* default service

<table style="text-align: center;">
<tr>
<th>name</th>
<th>typeParams</th>
<th>methods</th>
<th>description</th>
</tr>
<tr>
<td rowspan=4 style="vertical-align: middle;"><code>AlertService</code></td>
<td rowspan=4 style="vertical-align: middle;"><code>&lt;I&gt;</code></td>
<td><code>alertAll(Collection&lt;I&gt; idList, RestKey&lt;Integer&gt; keyType)</code></td>
<td rowspan=4 style="vertical-align: middle;">the service is used to handle <code>alert</code> and <code>alertAll</code> methods.</td>
</tr>
<tr>
<td><code>alertAll(String tablekey, Collection&lt;I&gt; idList, RestKey&lt;Integer&gt; keyType)</code></td>
</tr>
<tr>
<td><code>alertById(I id, RestKey&lt;Integer&gt; keyType)</code></td>
</tr>
<tr>
<td><code>alertById(String tablekey, I id, RestKey&lt;Integer&gt; keyType)</code></td>
</tr>
<tr>
<td rowspan=4 style="vertical-align: middle;"><code>DeleteService</code></td>
<td rowspan=4 style="vertical-align: middle;"><code>&lt;I,K&gt;</code></td>
<td><code>deleteAll(Collection&lt;I&gt; idList)</code></td>
<td rowspan=4 style="vertical-align: middle;">the service is used to handle <code>delete</code> and <code>deleteAll</code> methods.</td>
</tr>
<tr>
<td><code>deleteAll(K tablekey, Collection&lt;I&gt; idList)</code></td>
</tr>
<tr>
<td><code>deleteById(I id)</code></td>
</tr>
<tr>
<td><code>deleteById(K tablekey, I id)</code></td>
</tr>
<tr>
<td rowspan=4 style="vertical-align: middle;"><code>OperateService</code></td>
<td rowspan=4 style="vertical-align: middle;"><code>&lt;I,K&gt;</code></td>
<td><code>operateAll(Collection&lt;I&gt; idList, OperateType operateType)</code></td>
<td rowspan=4 style="vertical-align: middle;">the service is used to handle <code>operate</code> and <code>operateAll</code> methods.</td>
</tr>
<tr>
<td><code>operateAll(K tablekey, Collection&lt;I&gt; idList, OperateType operateType)</code></td>
</tr>
<tr>
<td><code>operateById(I id, OperateType operateType)</code></td>
</tr>
<tr>
<td><code>operateById(K tablekey, I id, OperateType operateType)</code></td>
</tr>
<tr>
<td rowspan=4 style="vertical-align: middle;"><code>RemoveService</code></td>
<td rowspan=4 style="vertical-align: middle;"><code>&lt;I,K&gt;</code></td>
<td><code>removeAll(Collection&lt;I&gt; idList)</code></td>
<td rowspan=4 style="vertical-align: middle;">the service is used to handle <code>remove</code> and <code>removeAll</code> methods.</td>
</tr>
<tr>
<td><code>removeAll(K tablekey, Collection&lt;I&gt; idList)</code></td>
</tr>
<tr>
<td><code>removeById(I id)</code></td>
</tr>
<tr>
<td><code>removeById(K tablekey, I id)</code></td>
</tr>
<tr>
<td rowspan=2 style="vertical-align: middle;"><code>NameService</code></td>
<td rowspan=2 style="vertical-align: middle;"><code>&lt;M extends RestId&lt;I&gt;, I, K&gt;</code></td>
<td><code>queryByName(String name, Boolean... isLoadArray)</code></td>
<td rowspan=2 style="vertical-align: middle;">the service is used to handle <code>query</code>  methods.</td>
</tr>
<tr>
<td><code>queryByName(K tablekey, String name, Boolean... isLoadArray)</code></td>
</tr>
<tr>
<td rowspan=4 style="vertical-align: middle;"><code>SingleService</code></td>
<td rowspan=4 style="vertical-align: middle;"><code>&lt;M extends RestId&lt;I&gt;, I, K&gt;</code></td>
<td><code>create(M model, Object... idArray)</code></td>
<td rowspan=4 style="vertical-align: middle;">the service is used to handle <code>create</code> and <code>update</code> methods.</td>
</tr>
<tr>
<td><code>create(K tablekey, M model, Object... idArray)</code></td>
</tr>
<tr>
<td><code>update(M model, Object... idArray)</code></td>
</tr>
<tr>
<td><code>update(K tablekey, M model, Object... idArray)</code></td>
</tr>
<tr>
<td rowspan=4 style="vertical-align: middle;"><code>SaveService</code></td>
<td rowspan=4 style="vertical-align: middle;"><code>&lt;M extends RestId&lt;I&gt;, I, K&gt;</code></td>
<td><code>save(M model, Object... idArray)</code></td>
<td rowspan=4 style="vertical-align: middle;">the service is used to handle <code>save</code> and <code>saveAll</code> methods.</td>
</tr>
<tr>
<td><code>save(K tablekey, M model, Object... idArray)</code></td>
</tr>
<tr>
<td><code>saveAll(Collection&lt;M&gt; modelList, Object... idArray)</code></td>
</tr>
<tr>
<td><code>saveAll(K tablekey, Collection&lt;M&gt; modelList, Object... idArray)</code></td>
</tr>
<tr>
<td rowspan=4 style="vertical-align: middle;"><code>QueryService</code></td>
<td rowspan=4 style="vertical-align: middle;"><code>&lt;M extends RestId&lt;I&gt;, I, K&gt;</code></td>
<td><code>queryAll(Collection&lt;I&gt; idList, Boolean... isLoadArray)</code></td>
<td rowspan=4 style="vertical-align: middle;">the service is used to handle <code>query</code> and <code>queryAll</code> methods.</td>
</tr>
<tr>
<td><code>queryAll(K tablekey, Collection&lt;I&gt; idList, Boolean... isLoadArray)</code></td>
</tr>
<tr>
<td><code>queryById(I id, Boolean... isLoadArray)</code></td>
</tr>
<tr>
<td><code>queryById(K tablekey, I id, Boolean... isLoadArray)</code></td>
</tr>
<tr>
<td rowspan=4 style="vertical-align: middle;"><code>FilterService</code></td>
<td rowspan=4 style="vertical-align: middle;"><code>&lt;M extends RestId&lt;I&gt;, F extends IdFilter&lt;I,K&gt;,I,K&gt;</code></td>
<td><code>queryAllWithFilter(F filter)</code></td>
<td rowspan=4 style="vertical-align: middle;">the service is used to handle <code>queryAll</code>、<code>deleteAll</code>、<code>removeAll</code> and <code>operateAll</code> methods.</td>
</tr>
<tr>
<td><code>deleteAllWithFilter(F filter)</code></td>
</tr>
<tr>
<td><code>removeAllWithFilter(F filter)</code></td>
</tr>
<tr>
<td><code>operateAllWithFilter(F filter)</code></td>
</tr>
<tr>
<td><code>SuperService</code></td>
<td><code>&lt;M extends RestId&lt;I&gt;, E extends RestId&lt;I&gt;, F extends IdFilter&lt;I, K&gt;, I, K&gt;</code></td>
<td></td>
<td>the service is default super service.</td>
</tr>
<tr>
<td><code>InfoService</code></td>
<td><code>&lt;M extends InfoModel&lt;I&gt;, E extends InfoEntity&lt;I&gt;, F extends IdFilter&lt;I, K&gt;, I, K&gt;</code></td>
<td></td>
<td>the service is default info service.</td>
</tr>
</table>

* extend service

<table style="text-align: center;">
<tr>
<th>name</th>
<th>typeParams</th>
<th>methods</th>
<th>description</th>
</tr>
<tr>
<td rowspan=7 style="vertical-align: middle;"><code>OptionalService</code></td>
<td rowspan=7 style="vertical-align: middle;"><code>&lt;M extends RestId&lt;I&gt;, F extends IdFilter&lt;I, K&gt;, I, K&gt;</code></td>
<td><code>optional(@NonNull M model)</code></td>
<td rowspan=7 style="vertical-align: middle;">the service is used to handle <code>optional</code> and <code>exist</code> methods.</td>
</tr>
<tr>
<td><code>existById(I id)</code></td>
</tr>
<tr>
<td><code>existById(K tablekey, I id)</code></td>
</tr>
<tr>
<td><code>optionalQueryFilter(F filter)</code></td>
</tr>
<tr>
<td><code>optionalDeleteFilter(F filter)</code></td>
</tr>
<tr>
<td><code>optionalRemoveFilter(F filter)</code></td>
</tr>
<tr>
<td><code>optionalOperateFilter(F filter)</code></td>
</tr>
<tr>
<td rowspan=4 style="vertical-align: middle;"><code>OperateLinkService</code></td>
<td rowspan=4 style="vertical-align: middle;"><code>&lt;I&gt;</code></td>
<td><code>operateAllByLinkIds(Collection&lt;I&gt; linkIdList, OperateType operateType)</code></td>
<td rowspan=4 style="vertical-align: middle;">the service is used to handle <code>operate</code> and <code>operateAll</code> methods.</td>
</tr>
<tr>
<td><code>operateAllByLinkIds(String tablekey, Collection&lt;I&gt; linkIdList, OperateType operateType)</code></td>
</tr>
<tr>
<td><code>operateByLinkId(I linkId, OperateType operateType)</code></td>
</tr>
<tr>
<td><code>operateByLinkId(String tablekey, I linkId, OperateType operateType)</code></td>
</tr>
<tr>
<td rowspan=4 style="vertical-align: middle;"><code>DeleteLinkService</code></td>
<td rowspan=4 style="vertical-align: middle;"><code>&lt;I&gt;</code></td>
<td><code>deleteAllByLinkIds(Collection&lt;I&gt; linkIdList)</code></td>
<td rowspan=4 style="vertical-align: middle;">the service is used to handle <code>delete</code> and <code>deleteAll</code> methods.</td>
</tr>
<tr>
<td><code>deleteAllByLinkIds(String tablekey, Collection&lt;I&gt; linkIdList)</code></td>
</tr>
<tr>
<td><code>deleteByLinkId(I linkId)</code></td>
</tr>
<tr>
<td><code>deleteByLinkId(String tablekey, I linkId)</code></td>
</tr>
<tr>
<td rowspan=4 style="vertical-align: middle;"><code>RemoveLinkService</code></td>
<td rowspan=4 style="vertical-align: middle;"><code>&lt;I, K&gt;</code></td>
<td><code>removeAllByLinkIds(Collection&lt;I&gt; linkIdList)</code></td>
<td rowspan=4 style="vertical-align: middle;">the service is used to handle <code>remove</code> and <code>removeAll</code> methods.</td>
</tr>
<tr>
<td><code>removeAllByLinkIds(K tablekey, Collection&lt;I&gt; linkIdList)</code></td>
</tr>
<tr>
<td><code>removeByLinkId(I linkId)</code></td>
</tr>
<tr>
<td><code>removeByLinkId(K tablekey, I linkId)</code></td>
</tr>
<tr>
<td rowspan=4 style="vertical-align: middle;"><code>AlertFieldService</code></td>
<td rowspan=4 style="vertical-align: middle;"><code>&lt;I&gt;</code></td>
<td><code>alertFieldAll(Collection&lt;I&gt; idList, String field, RestKey&lt;Integer&gt; keyType)</code></td>
<td rowspan=4 style="vertical-align: middle;">the service is used to handle <code>alert</code> and <code>alertAll</code> methods.</td>
</tr>
<tr>
<td><code>alertFieldAll(String tablekey, Collection&lt;I&gt; idList, String field, RestKey&lt;Integer&gt; keyType)</code></td>
</tr>
<tr>
<td><code>alertFieldById(I id, String field, RestKey&lt;Integer&gt; keyType)</code></td>
</tr>
<tr>
<td><code>alertFieldById(String tablekey, I id, String field, RestKey&lt;Integer&gt; keyType)</code></td>
</tr>
</table>

* id & info service

<table style="text-align: center;">
<tr>
<th>name</th>
<th>typeParams</th>
<th>description</th>
</tr>
<tr>
<td style="vertical-align: middle;"><code>DefaultIdService</code></td>
<td style="vertical-align: middle;"><code>&lt;M extends DefaultIdModel&lt;M, E, I&gt;, E extends DefaultIdEntity&lt;E, M, I&gt;, F extends DefaultFilter&lt;I, K&gt;, I, K&gt;</code></td>
<td style="vertical-align: middle;">the service is abstract id service, the <code>id</code> and <code>tablekey</code> type can be any object.</td>
</tr>
<tr>
<td style="vertical-align: middle;"><code>DefaultInfoService</code></td>
<td style="vertical-align: middle;"><code>&lt;M extends DefaultInfoModel&lt;M, E, I&gt;, E extends DefaultInfoEntity&lt;E, M, I&gt;, F extends DefaultFilter&lt;I, K&gt;, I, K&gt;</code></td>
<td style="vertical-align: middle;">the service is abstract info service, the <code>id</code> and <code>tablekey</code> type can be any object.</td>
</tr>
<tr>
<td style="vertical-align: middle;"><code>RestIdService</code></td>
<td style="vertical-align: middle;"><code>&lt;M extends RestIdModel&lt;M, E&gt;, E extends RestIdEntity&lt;E, M&gt;, F extends RestFilter&gt;</code></td>
<td style="vertical-align: middle;">the service is abstract id service, the <code>id</code> and <code>tablekey</code> type is default <code>String</code>.</td>
</tr>
<tr>
<td style="vertical-align: middle;"><code>RestInfoService</code></td>
<td style="vertical-align: middle;"><code>&lt;M extends RestInfoModel&lt;M, E&gt;, E extends RestInfoEntity&lt;E, M&gt;, F extends RestFilter&gt;</code></td>
<td style="vertical-align: middle;">the service is abstract info service, the <code>id</code> and <code>tablekey</code> type is default <code>String</code>.</td>
</tr>
</table>

* examples

```java
public interface SimpleService extends FilterService<SimpleModel, SimpleFilter, String, String>, SingleService<SimpleModel, String, String> {
}
```