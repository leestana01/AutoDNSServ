package kr.klr.dnsserv.Controller;

import kr.klr.dnsserv.Dto.DNSRecordDTO;
import kr.klr.dnsserv.Dto.RedirectDTO;
import kr.klr.dnsserv.Service.DnsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1")
public class DnsController {

    private final DnsService dnsService;

    @PostMapping("/records")
    public ResponseEntity<?> addDNSRecord(@RequestBody DNSRecordDTO dnsRecordDTO){
        dnsService.addDNSRecord(dnsRecordDTO);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/redirects")
    public ResponseEntity<?> addRedirects(@RequestBody RedirectDTO redirectDTO){
        dnsService.addRedirect(redirectDTO);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/subdomain/{subdomain}")
    public ResponseEntity<?> delDomain(@PathVariable String subdomain){
        dnsService.delDomain(subdomain);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
