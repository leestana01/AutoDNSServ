package Service;

import Domain.Dto.DNSRecordDTO;
import Domain.Dto.DeleteDomainDTO;
import Domain.Dto.RedirectDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
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
            System.out.println("error");
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
