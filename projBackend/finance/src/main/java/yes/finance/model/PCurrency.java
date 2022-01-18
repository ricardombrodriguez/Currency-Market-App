package yes.finance.model;

public interface PCurrency {
    Integer getId();

    String getName();

    String getSymbol();

    String getLogo_url();

    Boolean getOnline();

    Float getQuantity();

    Float getVolume();
}