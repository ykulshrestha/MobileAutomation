package enums;

public enum ChatUserEnums {

    RAMAN_SELLER("0c82d45b-c99b-4e9b-ab5f-cda2393b1bb4"),
    TEST_BUYER("ce60d2ff-a37e-48c9-a31b-886d47884680"),
    OWNERPROFILE_BUYER("de70ee5b-1f85-41a4-befa-e8858bbf2516"),
    SELLERONE_BUYER("315cae1e-9a00-491e-a9a7-8fb7d67d3fa0");


    private final String value;
    ChatUserEnums(String value) {
        this.value = value;
    }

    public String value()
    {
        return value;
    }

}
