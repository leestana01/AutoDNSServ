package kr.klr.dnsserv.Dto;

import lombok.Data;

@Data
public class RedirectDTO {
    private String subdomain;
    private String targetAddress;
}
