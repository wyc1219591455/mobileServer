package com.justech.mobile.mobileserver.elasticsearch.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.util.StringUtils;

/**
 * @ClassName AssetsEntity
 * @Author: xiaofeng.yang
 * @Date: Create in 12:17 2019/10/9
 * @Description : TODO
 * @Version: 1.0
 */
@Document(indexName = "assets", type = "assets")
@Getter
@Setter
@NoArgsConstructor
public class AssetsEntity {
    @Id
    private String id;
    @JSONField(name = "F_AssetsID")
    private String assetsid;
    @JSONField(name = "F_Name")
    private String name;
    @JSONField(name = "F_Model")
    private String model;
    @JSONField(name = "F_AddDate",serialize = false)
    private String addDate;
    @JSONField(name = "F_Sequence")
    private String sequence;
    @JSONField(name = "F_CreatorName")
    private String creatorName;
    @JSONField(name = "F_CreateDateTime",serialize = false)
    private String createDateTime;
    @JSONField(name = "F_DocId")
    private String docId;
    @JSONField(name = "F_TypeName")
    private String typeName;
    @JSONField(name = "F_PositionName")
    private String positionName;
    @JSONField(name = "F_DeptName")
    private String deptName;
    @JSONField(name = "F_KeeperName")
    private String keeperName;
    @JSONField(name = "F_KeeperID")
    private String keeperID;
    @JSONField(name = "F_Brand",serialzeFeatures = SerializerFeature.WriteMapNullValue)
    private String brand;
    private String version;

    @Override
    public int hashCode() {
        return this.assetsid.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        AssetsEntity assetsEntity= (AssetsEntity) obj;
        return this.assetsid.equals(assetsEntity.getAssetsid());
    }
}
