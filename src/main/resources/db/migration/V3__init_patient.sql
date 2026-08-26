create table Patient
(
    id             int auto_increment
        primary key,
    Name           Varchar(100) not null comment 'what would he like t be called',
    date_of_birth  date        not null comment 'get age from here',
    gender         enum ('male', 'female') not null comment '0 = female, 1 = male',
    phone_number varchar(15) not null comment 'should contain phone number of the patient not client but wont be enforced',
    chronic_diseases SET ('Hyperlipidemia', 'Obesity', 'Hypertension', 'Metabolic syndrome', 'Type 2 Diabetes mellitus', 'Chronic low back pain syndromes', 'Osteoarthritis', 'Bronchial asthma', 'Ischemic heart disease', 'Atherosclerosis', 'Rheumatoid arthritis', 'Gout', 'Hypothyroidism', 'Chronic kidney disease', 'Chronic Obstructive Pulmonary Disease', 'Heart failure', 'Cerebrovascular disease', 'Deep vein thrombosis', 'Peripheral artery disease', 'Chronic bronchitis', 'Osteoporosis', 'Type 1 Diabetes mellitus', 'Hyperthyroidism', 'Emphysema', 'Interstitial lung disease', 'Rheumatic heart disease', 'Bronchiectasis', 'Breast cancer', 'Colorectal cancer', 'Lung cancer', 'Lymphoma', 'none')
        default 'none' not null comment 'a set containing all possible chronic diseases NOTE: PLEASE FILL OUT ALL POSSIBLE VALUES N SET do not LEAVE EMPTY',
    client          int not null comment 'client associated with patient'
    #comment 'add tools'
    #comment 'add drugs'
    #comment 'add status after clarification'
);