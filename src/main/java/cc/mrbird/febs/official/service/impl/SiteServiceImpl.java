package cc.mrbird.febs.official.service.impl;

import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.official.entity.Site;
import cc.mrbird.febs.official.mapper.SiteMapper;
import cc.mrbird.febs.official.service.ISiteService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.beans.factory.annotation.Autowired;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * 站点管理 Service实现
 *
 * @author mgzu
 * @date 2019-09-22 21:06:36
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class SiteServiceImpl extends ServiceImpl<SiteMapper, Site> implements ISiteService {

    @Autowired
    private SiteMapper siteMapper;

    @Override
    public IPage<Site> findSites(QueryRequest request, Site site) {
        LambdaQueryWrapper<Site> queryWrapper = new LambdaQueryWrapper<>();
        // TODO 设置查询条件
        Page<Site> page = new Page<>(request.getPageNum(), request.getPageSize());
        return this.page(page, queryWrapper);
    }

    @Override
    public List<Site> findSites(Site site) {
	    LambdaQueryWrapper<Site> queryWrapper = new LambdaQueryWrapper<>();
		// TODO 设置查询条件
		return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional
    public void createSite(Site site) {
        this.save(site);
    }

    @Override
    @Transactional
    public void updateSite(Site site) {
        this.saveOrUpdate(site);
    }

    @Override
    @Transactional
    public void deleteSite(Site site) {
        LambdaQueryWrapper<Site> wrapper = new LambdaQueryWrapper<>();
	    // TODO 设置删除条件
	    this.remove(wrapper);
	}
}
