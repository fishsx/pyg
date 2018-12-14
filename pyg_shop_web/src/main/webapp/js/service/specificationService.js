app.service("specificationService", function ($http) {
    //分页查询所有
    this.findByPage = function (pageNum,pageSize) {
        return $http.get('../specification/findByPage.do?pageNum=' + pageNum + '&pageSize=' + pageSize);
    };
    //根据条件分页查询
    this.findByCondition = function (pageNum, pageSize, conditionObj) {
        return $http.post("../specification/findByCondition.do?pageNum=" + pageNum + "&pageSize=" + pageSize, conditionObj);
    };
    //添加
    this.add = function (obj) {
        return $http.post('../specification/add.do', obj);
    };
    //根据id查找
    this.findOneById = function (id) {
        return $http.get("../specification/findOneById.do?id=" + id);
    };
    //修改
    this.update = function (obj) {
        return $http.post("../specification/update.do", obj);
    };
    //批量删除
    this.batchDelete = function (idsArr) {
        return $http.post("../specification/batchDelete.do", idsArr);
    };

    //查找规格Json列表 (用于select2的显示)
    this.findSpecJsonList = function () {
        return $http.get("../specification/findSpecJsonList.do");
    };

    //查找指定的规格数据
    this.findSpecOptionsJsonList = function (typeTemplateId) {
        return $http.get("../specification/findSpecOptionsJsonList.do?typeTemplateId=" + typeTemplateId);
    }

});