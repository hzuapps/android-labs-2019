package com.yzk.ordis.bean;

import java.util.List;

public class Sortie {

    /**
     * id : 5c97a981c9e75c63bf8b4f48
     * activation : 2019-03-24T16:00:01.510Z
     * startString : -23h 10m 20s
     * expiry : 2019-03-25T15:59:00.000Z
     * active : true
     * rewardPool : Sortie Rewards
     * variants : [{"boss":"Deprecated","planet":"Deprecated","missionType":"Extermination","modifier":"Eximus Stronghold","modifierDescription":"Eximus units have a much higher spawn rate in this mission. Some of their auras stack.","node":"Unda (Venus)"},{"boss":"Deprecated","planet":"Deprecated","missionType":"Excavation","modifier":"Enemy Physical Enhancement: Slash","modifierDescription":"Enemies can deal enhanced slash damage. Finishing damage is not resisted.","node":"Despina (Neptune)"},{"boss":"Deprecated","planet":"Deprecated","missionType":"Rescue","modifier":"Energy Reduction","modifierDescription":"Maximum Warframe Energy capacity is quartered. Energy Siphon is less effective.","node":"Zeipel (Lua)"}]
     * boss : Jackal
     * faction : Corpus
     * expired : false
     * eta : 48m 38s
     */

    private String id;
    private String activation;
    private String startString;
    private String expiry;
    private boolean active;
    private String rewardPool;
    private String boss;
    private String faction;
    private boolean expired;
    private String eta;
    private List<VariantsBean> variants;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getActivation() {
        return activation;
    }

    public void setActivation(String activation) {
        this.activation = activation;
    }

    public String getStartString() {
        return startString;
    }

    public void setStartString(String startString) {
        this.startString = startString;
    }

    public String getExpiry() {
        return expiry;
    }

    public void setExpiry(String expiry) {
        this.expiry = expiry;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getRewardPool() {
        return rewardPool;
    }

    public void setRewardPool(String rewardPool) {
        this.rewardPool = rewardPool;
    }

    public String getBoss() {
        return boss;
    }

    public void setBoss(String boss) {
        this.boss = boss;
    }

    public String getFaction() {
        return faction;
    }

    public void setFaction(String faction) {
        this.faction = faction;
    }

    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }

    public String getEta() {
        return eta;
    }

    public void setEta(String eta) {
        this.eta = eta;
    }

    public List<VariantsBean> getVariants() {
        return variants;
    }

    public void setVariants(List<VariantsBean> variants) {
        this.variants = variants;
    }

    public static class VariantsBean {
        /**
         * boss : Deprecated
         * planet : Deprecated
         * missionType : Extermination
         * modifier : Eximus Stronghold
         * modifierDescription : Eximus units have a much higher spawn rate in this mission. Some of their auras stack.
         * node : Unda (Venus)
         */

        private String boss;
        private String planet;
        private String missionType;
        private String modifier;
        private String modifierDescription;
        private String node;

        public String getBoss() {
            return boss;
        }

        public void setBoss(String boss) {
            this.boss = boss;
        }

        public String getPlanet() {
            return planet;
        }

        public void setPlanet(String planet) {
            this.planet = planet;
        }

        public String getMissionType() {
            return missionType;
        }

        public void setMissionType(String missionType) {
            this.missionType = missionType;
        }

        public String getModifier() {
            return modifier;
        }

        public void setModifier(String modifier) {
            this.modifier = modifier;
        }

        public String getModifierDescription() {
            return modifierDescription;
        }

        public void setModifierDescription(String modifierDescription) {
            this.modifierDescription = modifierDescription;
        }

        public String getNode() {
            return node;
        }

        public void setNode(String node) {
            this.node = node;
        }
    }
}
