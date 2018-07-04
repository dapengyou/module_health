package com.test.baselibrary.module;


import com.alibaba.fastjson.annotation.JSONField;
import com.contrarywind.interfaces.IPickerViewData;
//import com.bigkoo.pickerview.model.IPickerViewData;
//import com.bigkoo.pickerview.model.IPickerViewData;

import java.io.Serializable;
import java.util.List;

/**
 * 完整省市区数据
 * Created by Robin on 2017/4/1.
 */

public class Province implements Serializable,IPickerViewData {
    @JSONField(ordinal = 1)
    private Integer regionId;
    @JSONField(ordinal = 2)
    private String regionName;
    @JSONField(ordinal = 3)
    private Integer regionType; // 1：省；2：市；3：区/县
    @JSONField(ordinal = 4)
    private Integer parentId; // 上级地区id，如果是省或直辖市parentId=0
    @JSONField(ordinal = 5)
    private List<City> citys; //市

    public Integer getRegionId() {
        return regionId;
    }

    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public Integer getRegionType() {
        return regionType;
    }

    public void setRegionType(Integer regionType) {
        this.regionType = regionType;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public List<City> getCitys() {
        return citys;
    }

    public void setCitys(List<City> citys) {
        this.citys = citys;
    }

    @Override
    public String toString() {
        return "Province{" +
                "regionId=" + regionId +
                ", regionName='" + regionName + '\'' +
                ", regionType=" + regionType +
                ", parentId=" + parentId +
                ", citys=" + citys +
                '}';
    }

    @Override
    public String getPickerViewText() {
        return this.regionName;
    }


    public static class City {
        @JSONField(ordinal = 1)
        private Integer regionId;
        @JSONField(ordinal = 2)
        private String regionName;
        @JSONField(ordinal = 3)
        private Integer regionType; // 1：省；2：市；3：区/县
        @JSONField(ordinal = 4)
        private Integer parentId; // 上级地区id，如果是省或直辖市parentId=0
        @JSONField(ordinal = 5)
        private List<Districts> districts; //区

        public Integer getRegionId() {
            return regionId;
        }

        public void setRegionId(Integer regionId) {
            this.regionId = regionId;
        }

        public String getRegionName() {
            return regionName;
        }

        public void setRegionName(String regionName) {
            this.regionName = regionName;
        }

        public Integer getRegionType() {
            return regionType;
        }

        public void setRegionType(Integer regionType) {
            this.regionType = regionType;
        }

        public Integer getParentId() {
            return parentId;
        }

        public void setParentId(Integer parentId) {
            this.parentId = parentId;
        }

        public List<Districts> getDistricts() {
            return districts;
        }

        public void setDistricts(List<Districts> districts) {
            this.districts = districts;
        }
    }

    public static class Districts {
        @JSONField(ordinal = 1)
        private Integer regionId;
        @JSONField(ordinal = 2)
        private String regionName;
        @JSONField(ordinal = 3)
        private Integer regionType; // 1：省；2：市；3：区/县
        @JSONField(ordinal = 4)
        private Integer parentId; // 上级地区id，如果是省或直辖市parentId=0

        public Integer getRegionId() {
            return regionId;
        }

        public void setRegionId(Integer regionId) {
            this.regionId = regionId;
        }

        public String getRegionName() {
            return regionName;
        }

        public void setRegionName(String regionName) {
            this.regionName = regionName;
        }

        public Integer getRegionType() {
            return regionType;
        }

        public void setRegionType(Integer regionType) {
            this.regionType = regionType;
        }

        public Integer getParentId() {
            return parentId;
        }

        public void setParentId(Integer parentId) {
            this.parentId = parentId;
        }

    }
}
