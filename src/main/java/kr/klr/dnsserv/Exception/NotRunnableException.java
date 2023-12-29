package kr.klr.dnsserv.Exception;

public class NotRunnableException extends RuntimeException{
    public NotRunnableException(String filepath){
        super("파일 실행에 실패함: "+filepath);
    }
}
