package Domain.Dto;

import lombok.Data;

@Data
public class DNSRecordDTO {
    // 양식
    // subdomain    A   1.2.3.4
    // subdomain    CNAME   example.com. (뒤에 온점)
    private String subdomain;
    private String record;
    private String targetAddress;
}
