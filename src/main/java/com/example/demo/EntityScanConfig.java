package com.example.demo;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EntityScan("com.example.demo.entities")
public class EntityScanConfig {

}
