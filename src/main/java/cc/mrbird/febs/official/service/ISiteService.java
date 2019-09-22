package cc.mrbird.febs.official.service;

import cc.mrbird.febs.official.entity.Site;

import cc.mrbird.febs.common.entity.QueryRequest;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 站点管理 Service接口
 *
 * @author mgzu
 * @date 2019-09-22 21:06:36
 */
public interface ISiteService extends IService<Site> {
    /**
     * 查询（分页）
     *
     * @param request QueryRequest
     * @param site site
     * @return IPage<Site>
     */
    IPage<Site> findSites(QueryRequest request, Site site);

    /**
     * 查询（所有）
     *
     * @param site site
     * @return List<Site>
     */
    List<Site> findSites(Site site);

    /**
     * 新增
     *
     * @param site site
     */
    void createSite(Site site);

    /**
     * 修改
     *
     * @param site site
     */
    void updateSite(Site site);

    /**
     * 删除
     *
     * @param site site
     */
    void deleteSite(Site site);
}
