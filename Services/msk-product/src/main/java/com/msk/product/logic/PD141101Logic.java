package com.msk.product.logic;

import com.hoperun.core.bean.BaseParam;
import com.hoperun.core.exception.BusinessException;
import com.hoperun.jdbc.mybatis.BaseDao;
import com.msk.common.base.BaseLogic;
import com.msk.core.entity.PdBreed;
import com.msk.core.entity.PdFeature;
import com.msk.core.entity.PdStandard;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * PD141101Logic
 * @author gyh
 */
@Service
public class PD141101Logic extends BaseLogic {
    @Autowired
    private PD141102Logic pd141102Logic;
    @Autowired
    private PD141104Logic pd141104Logic;

    /**
     * SQL Map 中SQL ID定义
     * @author gyh
     */
    interface SqlId {
        String SQL_ID_FIND_BREED_LIST_BY_CLASSESID = "findListBreedList";
        String SQL_ID_FIND_lIST_PD_FEATURE = "findListFeature";
        String SQL_ID_FIND_STAND_ID = "findStandarId";//查询产品标准id
        String SQL_ID_DELETE_PD_TNC_STD_BY_STANDARID = "deleteTnc";//删除产品技术标准
        String SQL_ID_DELETE_PD_NORMS_STD_BY_STANDARID = "deleteNorms"; //删除产品包装表中数据
        String SQL_ID_DELETE_PD_QLT_STD_BY_STANDARID = "deleteQlt"; //删除产品质量标准数据
        String SQL_ID_DELETE_PD_STANDARD_BY_STANDARID = "deleteStd"; //删除产品标准数据
        String SQL_ID_DELETE_PD_FEATURE = "deleteFea";//删除产品特征数据
        String SQL_ID_DELETE_PD_BREED_BY_ID = "deleteBreed"; //删除产品品种数据
        String SQL_ID_DELETE_PD_CLASSES_BY_ID = "deleteClasses"; //删除产品品种数据
    }

    /**
     * 类别品种和特征信息查询
     * 方法未被调用(留着备用)
     * @param param param
     * @return 产品信息
     * @author gyh
     */
   /* public List<ProductClasses> findResult(BaseParam param) {
        // 查询产品类别
        List<ProductClasses> productClassesList = super.findPage(param, ProductClasses.class).getData();
        BasePageParam baseParam = new BasePageParam();
        for (ProductClasses productClasses : productClassesList) {
            baseParam.setFilter("classesCode", productClasses.getClassesCode());
            // 根据类别编码查询品种
            List<ProductBreed> productBreeds = new ArrayList<ProductBreed>();
            productBreeds = this.pd141102Logic.findPageList(baseParam, ProductBreed.class);
            for (ProductBreed productBreed : productBreeds) {
                baseParam.setFilter("breedCode", productBreed.getBreedCode());
                // 根据品种编码查询特征
                List<PdFeature> pdFeatures = new ArrayList<PdFeature>();
                pdFeatures = this.pd141104Logic.findPageList(baseParam, PdFeature.class);
                productBreed.setPdFeatureList(pdFeatures);
                productBreed.setSize(pdFeatures.size());
            }
            productClasses.setPdBreedList(productBreeds);
            productClasses.setSize(productBreeds.size());
        }
        return productClassesList;
    }*/

    /**
     * //删除商品类别同时删除商品品种和特征等废弃数据
     * @param param 参数
     * @return 修改行数
     */
    @Transactional(readOnly = false,
            propagation = Propagation.REQUIRED,
            rollbackFor = Exception.class)
    public int deleteClasses(BaseParam param) {
        //获取所有商品品种数据根据商品类别id查询
        List<PdBreed> breedList = this.findList(SqlId.SQL_ID_FIND_BREED_LIST_BY_CLASSESID,param);
        //获取对应的商品特征数据
        for (PdBreed breed : breedList){
            BaseParam param1 = new BaseParam();
            if(StringUtils.isNotEmpty(breed.getBreedCode())&&StringUtils.isNotEmpty(breed.getClassesCode())){
                param1.setFilter("classesCode",breed.getClassesCode());
                param1.setFilter("breedCode",breed.getBreedCode());
            }else {
                throw new BusinessException("数据异常,请联系管理员");
            }
            //获取当前产品数据的所有特征数据
            List<PdFeature> features = this.findList(SqlId.SQL_ID_FIND_lIST_PD_FEATURE,param1);
            //循环删除数据
            for (PdFeature feature : features){
                BaseParam param2 = new BaseParam();
                param2.setFilter("classesCode",feature.getClassesCode());
                System.out.println(feature.getClassesCode());
                param2.setFilter("breedCode",feature.getBreedCode());
                System.out.println(feature.getBreedCode());
                param2.setFilter("featureCode",feature.getFeatureCode());
                System.out.println(feature.getFeatureCode());

                //获取产品标准id
                PdStandard pdStandard = this.findOne(SqlId.SQL_ID_FIND_STAND_ID, param2);
                //根据产品标准id删除对应数据 删除产品技术标准表 PD_TNC_STD
                this.remove(SqlId.SQL_ID_DELETE_PD_TNC_STD_BY_STANDARID,pdStandard);
                //根据产品标准id删除对应数据 删除产品包装表中数据 PD_NORMS_STD
                this.remove(SqlId.SQL_ID_DELETE_PD_NORMS_STD_BY_STANDARID,pdStandard);
                //根据产品标准id删除对应数据 删除产品质量标准数据 PD_QLT_STD
                this.remove(SqlId.SQL_ID_DELETE_PD_QLT_STD_BY_STANDARID,pdStandard);
                //删除产品标准数据
                this.remove(SqlId.SQL_ID_DELETE_PD_STANDARD_BY_STANDARID,pdStandard);
                //删除产品特征数据
                this.remove(SqlId.SQL_ID_DELETE_PD_FEATURE,param2);
            }
            this.remove(SqlId.SQL_ID_DELETE_PD_BREED_BY_ID,param1);
        }
        int rs = this.remove(SqlId.SQL_ID_DELETE_PD_CLASSES_BY_ID,param);
        return rs;
    }
   /**
     * 类别信息假数据
     * @return 产品分类信息  
     * @author gyh
     *//*
    public List<ProductClasses> getProductClassesInfo() {
        List<ProductClasses> productClasseList = new ArrayList<ProductClasses>();
        List<ProductBreed> pdBreedList = new ArrayList<ProductBreed>();
        List<PdFeature> pdFeatureList = new ArrayList<PdFeature>();
        PdFeature pdFeature1 = new PdFeature();
        pdFeature1.setClassesCode("01");
        pdFeature1.setBreedCode("03");
        pdFeature1.setFeatureCode("01");
        pdFeature1.setFeatureName("80-100g");
        pdFeature1.setDelFlg("否");
        pdFeatureList.add(pdFeature1);
        PdFeature pdFeature2 = new PdFeature();
        pdFeature2.setClassesCode("01");
        pdFeature2.setBreedCode("03");
        pdFeature2.setFeatureCode("02");
        pdFeature2.setFeatureName("100-120g");
        pdFeature2.setDelFlg("否");
        pdFeatureList.add(pdFeature2);

        PdFeature pdFeature3 = new PdFeature();
        pdFeature3.setClassesCode("01");
        pdFeature3.setBreedCode("03");
        pdFeature3.setFeatureCode("03");
        pdFeature3.setFeatureName("120-130g");
        pdFeature3.setDelFlg("否");
        pdFeatureList.add(pdFeature3);

        PdFeature pdFeature4 = new PdFeature();
        pdFeature4.setClassesCode("01");
        pdFeature4.setBreedCode("03");
        pdFeature4.setFeatureCode("04");
        pdFeature4.setFeatureName("130-150g");
        pdFeature4.setDelFlg("否");
        pdFeatureList.add(pdFeature4);

        PdFeature pdFeature5 = new PdFeature();
        pdFeature5.setClassesCode("01");
        pdFeature5.setBreedCode("03");
        pdFeature5.setFeatureCode("05");
        pdFeature5.setFeatureName("150g以上");
        pdFeature5.setDelFlg("否");
        pdFeatureList.add(pdFeature5);

        ProductBreed productBreed1 = new ProductBreed();
        productBreed1.setClassesCode("01");
        productBreed1.setBreedCode("01");
        productBreed1.setBreedName("鸡产品三黄土鸡");
        productBreed1.setDelFlg("否");
        pdBreedList.add(productBreed1);

        ProductBreed productBreed2 = new ProductBreed();
        productBreed2.setClassesCode("01");
        productBreed2.setBreedCode("02");
        productBreed2.setBreedName("鸡产品单冻鸡大胸");
        productBreed2.setDelFlg("否");
        pdBreedList.add(productBreed2);

        ProductBreed productBreed3 = new ProductBreed();
        productBreed3.setClassesCode("01");
        productBreed3.setBreedCode("03");
        productBreed3.setBreedName("单冻琵琶腿");
        productBreed3.setDelFlg("否");
        productBreed3.setPdFeatureList(pdFeatureList);
        pdBreedList.add(productBreed3);

        ProductBreed productBreed4 = new ProductBreed();
        productBreed4.setClassesCode("01");
        productBreed4.setBreedCode("04");
        productBreed4.setBreedName("鸡产品鸡边腿");
        productBreed4.setDelFlg("否");
        pdBreedList.add(productBreed4);

        ProductBreed productBreed5 = new ProductBreed();
        productBreed5.setClassesCode("01");
        productBreed5.setBreedCode("05");
        productBreed5.setBreedName("鸡产品鸡排腿");
        productBreed5.setDelFlg("否");
        pdBreedList.add(productBreed5);

        ProductBreed productBreed6 = new ProductBreed();
        productBreed6.setClassesCode("01");
        productBreed6.setBreedCode("06");
        productBreed6.setBreedName("鸡产品鸡全翅");
        productBreed6.setDelFlg("否");
        pdBreedList.add(productBreed6);

        ProductBreed productBreed7 = new ProductBreed();
        productBreed7.setClassesCode("01");
        productBreed7.setBreedCode("07");
        productBreed7.setBreedName("鸡产品鸡翅中");
        productBreed7.setDelFlg("否");
        pdBreedList.add(productBreed7);

        ProductBreed productBreed8 = new ProductBreed();
        productBreed8.setClassesCode("01");
        productBreed8.setBreedCode("08");
        productBreed8.setBreedName("鸡产品鸡翅尖");
        productBreed8.setDelFlg("否");
        pdBreedList.add(productBreed8);

        ProductBreed productBreed9 = new ProductBreed();
        productBreed9.setClassesCode("01");
        productBreed9.setBreedCode("09");
        productBreed9.setBreedName("鸡产品鸡翅根");
        productBreed9.setDelFlg("否");
        pdBreedList.add(productBreed9);

        ProductBreed productBreed10 = new ProductBreed();
        productBreed10.setClassesCode("01");
        productBreed10.setBreedCode("10");
        productBreed10.setBreedName("鸡产品鸡架");
        productBreed10.setDelFlg("否");
        pdBreedList.add(productBreed10);

        ProductBreed productBreed11 = new ProductBreed();
        productBreed11.setClassesCode("01");
        productBreed11.setBreedCode("11");
        productBreed11.setBreedName("鸡产品鸡爪");
        productBreed11.setDelFlg("否");
        pdBreedList.add(productBreed11);

        ProductBreed productBreed12 = new ProductBreed();
        productBreed12.setClassesCode("01");
        productBreed12.setBreedCode("12");
        productBreed12.setBreedName("鸡产品凤爪");
        productBreed12.setDelFlg("否");
        pdBreedList.add(productBreed12);

        ProductBreed productBreed13 = new ProductBreed();
        productBreed13.setClassesCode("01");
        productBreed13.setBreedCode("13");
        productBreed13.setBreedName("鸡产品鸡肝");
        productBreed13.setDelFlg("否");
        pdBreedList.add(productBreed13);

        ProductBreed productBreed14 = new ProductBreed();
        productBreed14.setClassesCode("01");
        productBreed14.setBreedCode("14");
        productBreed14.setBreedName("鸡产品鸡心");
        productBreed14.setDelFlg("否");
        pdBreedList.add(productBreed14);

        ProductBreed productBreed15 = new ProductBreed();
        productBreed15.setClassesCode("01");
        productBreed15.setBreedCode("15");
        productBreed15.setBreedName("鸡产品掌中宝");
        productBreed15.setDelFlg("否");
        pdBreedList.add(productBreed15);

        ProductBreed productBreed16 = new ProductBreed();
        productBreed16.setClassesCode("01");
        productBreed16.setBreedCode("16");
        productBreed16.setBreedName("鸡产品鸡上腿肉");
        productBreed16.setDelFlg("否");
        pdBreedList.add(productBreed16);

        ProductBreed productBreed17 = new ProductBreed();
        productBreed17.setClassesCode("01");
        productBreed17.setBreedCode("17");
        productBreed17.setBreedName("鸡产品鸡丝");
        productBreed17.setDelFlg("否");
        pdBreedList.add(productBreed17);

        ProductBreed productBreed18 = new ProductBreed();
        productBreed18.setClassesCode("01");
        productBreed18.setBreedCode("18");
        productBreed18.setBreedName("鸡产品鸡丁");
        productBreed18.setDelFlg("否");
        pdBreedList.add(productBreed18);

        ProductBreed productBreed19 = new ProductBreed();
        productBreed19.setClassesCode("01");
        productBreed19.setBreedCode("19");
        productBreed19.setBreedName("鸡产品鸡片");
        productBreed19.setDelFlg("否");
        pdBreedList.add(productBreed19);

        ProductBreed productBreed20 = new ProductBreed();
        productBreed20.setClassesCode("01");
        productBreed20.setBreedCode("20");
        productBreed20.setBreedName("鸡产品鸡排腿块");
        productBreed20.setDelFlg("否");
        pdBreedList.add(productBreed20);

        ProductBreed productBreed21 = new ProductBreed();
        productBreed21.setClassesCode("01");
        productBreed21.setBreedCode("21");
        productBreed21.setBreedName("鸡产品鸡琵琶腿");
        productBreed21.setDelFlg("否");
        pdBreedList.add(productBreed21);

        ProductClasses productClasses1 = new ProductClasses();
        productClasses1.setClassesCode("01");
        productClasses1.setClassesName("鸡产品");
        productClasses1.setDelFlg("否");
        productClasses1.setPdBreedList(pdBreedList);
        productClasseList.add(productClasses1);

        ProductClasses productClasses2 = new ProductClasses();
        productClasses2.setClassesCode("02");
        productClasses2.setClassesName("鸭产品");
        productClasses2.setDelFlg("否");
        productClasseList.add(productClasses2);

        ProductClasses productClasses3 = new ProductClasses();
        productClasses3.setClassesCode("03");
        productClasses3.setClassesName("猪产品");
        productClasses3.setDelFlg("否");
        productClasseList.add(productClasses3);

        ProductClasses productClasses4 = new ProductClasses();
        productClasses4.setClassesCode("04");
        productClasses4.setClassesName("牛羊产品");
        productClasses4.setDelFlg("否");
        productClasseList.add(productClasses4);

        ProductClasses productClasses5 = new ProductClasses();
        productClasses5.setClassesCode("05");
        productClasses5.setClassesName("海产品");
        productClasses5.setDelFlg("否");
        productClasseList.add(productClasses5);

        ProductClasses productClasses6 = new ProductClasses();
        productClasses6.setClassesCode("06");
        productClasses6.setClassesName("丸肠水发品");
        productClasses6.setDelFlg("否");
        productClasseList.add(productClasses6);

        ProductClasses productClasses7 = new ProductClasses();
        productClasses7.setClassesCode("07");
        productClasses7.setClassesName("腌腊制品");
        productClasses7.setDelFlg("否");
        productClasseList.add(productClasses7);

        ProductClasses productClasses8 = new ProductClasses();
        productClasses8.setClassesCode("08");
        productClasses8.setClassesName("冰品类");
        productClasses8.setDelFlg("否");
        productClasseList.add(productClasses8);

        ProductClasses productClasses9 = new ProductClasses();
        productClasses9.setClassesCode("09");
        productClasses9.setClassesName("速冻点心");
        productClasses9.setDelFlg("否");
        productClasseList.add(productClasses9);

        ProductClasses productClasses10 = new ProductClasses();
        productClasses10.setClassesCode("10");
        productClasses10.setClassesName("速冻蔬菜");
        productClasses10.setDelFlg("否");
        productClasseList.add(productClasses10);

        ProductClasses productClasses11 = new ProductClasses();
        productClasses11.setClassesCode("11");
        productClasses11.setClassesName("粮油产品");
        productClasses11.setDelFlg("否");
        productClasseList.add(productClasses11);

        ProductClasses productClasses12 = new ProductClasses();
        productClasses12.setClassesCode("12");
        productClasses12.setClassesName("调味品");
        productClasses12.setDelFlg("否");
        productClasseList.add(productClasses12);

        ProductClasses productClasses13 = new ProductClasses();
        productClasses13.setClassesCode("13");
        productClasses13.setClassesName("干货产品");
        productClasses13.setDelFlg("否");
        productClasseList.add(productClasses13);

        ProductClasses productClasses14 = new ProductClasses();
        productClasses14.setClassesCode("14");
        productClasses14.setClassesName("小菜产品");
        productClasses14.setDelFlg("否");
        productClasseList.add(productClasses14);

        ProductClasses productClasses15 = new ProductClasses();
        productClasses15.setClassesCode("15");
        productClasses15.setClassesName("儿童食品");
        productClasses15.setDelFlg("否");
        productClasseList.add(productClasses15);

        ProductClasses productClasses16 = new ProductClasses();
        productClasses16.setClassesCode("16");
        productClasses16.setClassesName("调理水煮包与方便菜");
        productClasses16.setDelFlg("否");
        productClasseList.add(productClasses16);

        return productClasseList;
    }*/

    @Autowired
    @Override
    public void setBaseDao(BaseDao baseDao) {
        super.setBaseDao(baseDao);
    }
}
