package com.jwt.jwitter.web.service;

import com.jwt.jwitter.models.Report;
import com.jwt.jwitter.web.dto.in.ReportDto;
import com.jwt.jwitter.web.repository.ReportRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Data
public class ReportService {

    @Autowired
    ReportRepository reportRepository;

    @Transactional
    public Report reportPost(final ReportDto reportDto) {
        return this.reportRepository.reportPost(reportDto);
    }


}
