package com.inspire12.homepage.model.view_model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public class TableView {
    @JsonProperty("table_type")
    String tableType;

    @JsonProperty("table_title")
    String tableTitle;

    List<String> columns;
    List<ObjectNode> rows;

    PageRequest pager;
}
