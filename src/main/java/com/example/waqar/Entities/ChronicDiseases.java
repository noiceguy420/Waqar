package com.example.waqar.Entities;

public enum ChronicDiseases{
        HYPERLIPIDEMIA("Hyperlipidemia"),
        OBESITY("Obesity"),
        HYPERTENSION("Hypertension"),
        METABOLIC_SYNDROME("Metabolic syndrome"),
        TYPE_2_DIABETES("Type 2 Diabetes mellitus"),
        CHRONIC_LOW_BACK_PAIN("Chronic low back pain syndromes"),
        OSTEOARTHRITIS("Osteoarthritis"),
        BRONCHIAL_ASTHMA("Bronchial asthma"),
        ISCHEMIC_HEART_DISEASE("Ischemic heart disease"),
        ATHEROSCLEROSIS("Atherosclerosis"),
        RHEUMATOID_ARTHRITIS("Rheumatoid arthritis"),
        GOUT("Gout"),
        HYPOTHYROIDISM("Hypothyroidism"),
        CHRONIC_KIDNEY_DISEASE("Chronic kidney disease"),
        COPD("Chronic Obstructive Pulmonary Disease"),
        HEART_FAILURE("Heart failure"),
        CEREBROVASCULAR_DISEASE("Cerebrovascular disease"),
        DEEP_VEIN_THROMBOSIS("Deep vein thrombosis"),
        PERIPHERAL_ARTERY_DISEASE("Peripheral artery disease"),
        CHRONIC_BRONCHITIS("Chronic bronchitis"),
        OSTEOPOROSIS("Osteoporosis"),
        TYPE_1_DIABETES("Type 1 Diabetes mellitus"),
        HYPERTHYROIDISM("Hyperthyroidism"),
        EMPHYSEMA("Emphysema"),
        INTERSTITIAL_LUNG_DISEASE("Interstitial lung disease"),
        RHEUMATIC_HEART_DISEASE("Rheumatic heart disease"),
        BRONCHIECTASIS("Bronchiectasis"),
        BREAST_CANCER("Breast cancer"),
        COLORECTAL_CANCER("Colorectal cancer"),
        LUNG_CANCER("Lung cancer"),
        LYMPHOMA("Lymphoma"),
        NONE("none");

        private final String dbValue;

        ChronicDiseases(String dbValue) {
                this.dbValue = dbValue;
        }

        public String getDbValue() {
                return dbValue;
        }

        public static ChronicDiseases fromDbValue(String dbValue) {
                for (ChronicDiseases d : values()) {
                        if (d.dbValue.equals(dbValue)) return d;
                }
                throw new IllegalArgumentException("Unknown chronic disease DB value: " + dbValue);
        }
}
