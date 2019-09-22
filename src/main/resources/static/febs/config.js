layui.define(function(exports) {
  exports('conf', {
    container: 'febs',
    containerBody: 'febs-body',
    v: '2.0',
    base: layui.cache.base,
    css: layui.cache.base + 'css/',
    views: templateRes + '/admin/',
    viewLoadBar: true,
    debug: layui.cache.debug,
    name: 'febs',
    entry: '/home',
    engine: '',
    eventName: 'febs-event',
    tableName: 'febs',
    requestUrl: './'
  })
});
