package enums;

public enum ChatUserEnums {

    RAMAN_SELLER("0c82d45b-c99b-4e9b-ab5f-cda2393b1bb4"),
    TEST_BUYER("ce60d2ff-a37e-48c9-a31b-886d47884680"),
    OWNERPROFILE_BUYER("de70ee5b-1f85-41a4-befa-e8858bbf2516"),
    SELLERONE("315cae1e-9a00-491e-a9a7-8fb7d67d3fa0"),
    PRABAL_SELLER("8f8f23af-d053-41ea-8ed5-9546b226d611"),
    YAMINI_BUYER("8305a298-56c4-43ca-a74c-e46c2df2ef9e"),
    TESTSELLER("eaab58dd-7983-4234-878b-1604489bede8"),
    ABC_BUYER("88d0de07-9958-49bd-8e19-5cbe3ff4c53f"),
    GUNPREET("de70ee5b-1f85-41a4-befa-e8858bbf2516");


    private final String value;
    ChatUserEnums(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public String value()
    {
        return value;
    }

}
