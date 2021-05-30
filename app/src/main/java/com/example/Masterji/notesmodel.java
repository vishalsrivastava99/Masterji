package com.example.Masterji;

public class notesmodel {

    String pdfUrl,pdftitle;

    public notesmodel() {
    }

    public notesmodel(String pdfUrl, String pdftitle) {
        this.pdfUrl = pdfUrl;
        this.pdftitle = pdftitle;
    }

    public String getPdfUrl() {
        return pdfUrl;
    }

    public void setPdfUrl(String pdfUrl) {
        this.pdfUrl = pdfUrl;
    }

    public String getPdftitle() {
        return pdftitle;
    }

    public void setPdftitle(String pdftitle) {
        this.pdftitle = pdftitle;
    }
}
