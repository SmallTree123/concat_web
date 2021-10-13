package com.nylgsc.model;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class Goods {
    private Long gid;
    private String gname;
    private int userId;
    private String gstatus;
}
