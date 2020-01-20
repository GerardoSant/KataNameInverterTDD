import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class NameInverterTest {

    @Test(expected=NullPointerException.class)
    public void invert_null_should_throw_NullPointerException(){
        NameInverter.invert(null);
    }

    @Test
    public void invert_empty_string_should_return_empty_string(){
        assertThat(NameInverter.invert("")).isEqualTo("");
        assertThat(NameInverter.invert("  ")).isEqualTo("");
    }

    @Test
    public void invert_first_name_should_return_first_name(){
        assertThat(NameInverter.invert("John")).isEqualTo("John");
        assertThat(NameInverter.invert("   John          ")).isEqualTo("John");
    }

    @Test
    public void invert_first_and_last_should_return_last_comma__first(){
        assertThat(NameInverter.invert("John Smith")).isEqualTo("Smith,John");
        assertThat(NameInverter.invert("John    Smith")).isEqualTo("Smith,John");
    }

    @Test
    public void invert_honorifics_first_and_last_should_return_last_comma_first(){
        assertThat(NameInverter.invert("Mr. John Smith")).isEqualTo("Smith,John");
        assertThat(NameInverter.invert("Ms. John Smith")).isEqualTo("Smith,John");
        assertThat(NameInverter.invert("Dr. John Smith")).isEqualTo("Smith,John");
    }

    @Test
    public void invert_first_last_postnominals_should_return_last_comma_first_postnominals(){
        assertThat(NameInverter.invert("John Smith Sr.")).isEqualTo("Smith,John Sr.");
        assertThat(NameInverter.invert("John Smith Sr. PhD.")).isEqualTo("Smith,John Sr. PhD.");
    }

    @Test
    public void invert_acceptanceTest(){
        assertThat(NameInverter.invert("Mr.        John  Smith    Sr.     PhD.")).isEqualTo("Smith,John Sr. PhD.");
    }

}
