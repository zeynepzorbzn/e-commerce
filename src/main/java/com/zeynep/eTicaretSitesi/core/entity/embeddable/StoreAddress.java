package com.zeynep.eTicaretSitesi.core.entity.embeddable;

import jakarta.persistence.Embeddable;

    @Embeddable
    public class StoreAddress {

        private String city;
        private String district;
        private String street;
        private String postalCode;

        public StoreAddress() {
        }

        public String getCity() {return city;}
        public void setCity(String city) {this.city = city;}

        public String getDistrict() {return district;}
        public void setDistrict(String district) {this.district = district;}

        public String getStreet() {return street;}
        public void setStreet(String street) {this.street = street;}

        public String getPostalCode() {return postalCode;}
        public void setPostalCode(String postalCode) {this.postalCode = postalCode;}
    }

