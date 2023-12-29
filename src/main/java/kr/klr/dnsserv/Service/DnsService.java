package kr.klr.dnsserv.Service;

import kr.klr.dnsserv.Dto.DNSRecordDTO;
import kr.klr.dnsserv.Dto.RedirectDTO;
import kr.klr.dnsserv.Exception.NotRunnableException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class DnsService {

    private void executeScript(String scriptPath, String... args){
        try {
            List<String> command = new ArrayList<>();
            command.add("sudo");
            command.add(scriptPath);
            Collections.addAll(command, args);

            ProcessBuilder processBuilder = new ProcessBuilder(command);
            Process process = processBuilder.start();
            process.waitFor();
        } catch (Exception e) {
            throw new NotRunnableException(scriptPath);
        }
    }
    public void addDNSRecord(DNSRecordDTO dnsRecordDTO){
        String targetAddress = dnsRecordDTO.getTargetAddress();
        if(dnsRecordDTO.getRecord().equals("CNAME")){
            targetAddress = targetAddress+ ".";
        }
        executeScript("/etc/bind/zones/add_record.sh", dnsRecordDTO.getSubdomain(), dnsRecordDTO.getRecord(), targetAddress);
    }

    public void addRedirect(RedirectDTO redirectDTO){
        // /etc/nginx/conf.d/add_redirects.sh 실행 로직
        executeScript("/etc/nginx/conf.d/add_redirects.sh", redirectDTO.getSubdomain(), redirectDTO.getTargetAddress());
    }

    public void delDomain(String subDomain){
        executeScript("/etc/nginx/conf.d/del_redirects.sh", subDomain);
    }
}
