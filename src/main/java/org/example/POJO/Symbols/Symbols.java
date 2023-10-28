package org.example.POJO.Symbols;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "success",
        "symbols"
})
@Generated("jsonschema2pojo")
public class Symbols {

    @JsonProperty("success")
    private Boolean success;
    @JsonProperty("symbols")
    private Symbols__1 symbols;

    @JsonProperty("success")
    public Boolean getSuccess() {
        return success;
    }

    @JsonProperty("success")
    public void setSuccess(Boolean success) {
        this.success = success;
    }

    @JsonProperty("symbols")
    public Symbols__1 getSymbols() {
        return symbols;
    }

    @JsonProperty("symbols")
    public void setSymbols(Symbols__1 symbols) {
        this.symbols = symbols;
    }

}