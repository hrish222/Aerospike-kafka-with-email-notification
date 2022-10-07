package com.restapi.Configuration;

import com.aerospike.client.AerospikeClient;
import com.aerospike.client.policy.ClientPolicy;
import com.aerospike.mapper.tools.AeroMapper;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
public class aeroMapperConfig {
    @Inject
    aerospikeConfiguration config;

     AeroMapper mapper = null;

   // private static Logger logger = LogManager.getLogger(AeroMapperUtil.class.getName());

    @PostConstruct
    public void setupAerospikeClient() {

        ClientPolicy policy = new ClientPolicy();
        AerospikeClient client = new AerospikeClient(policy,config.getHost(), config.getPort());
        mapper = new AeroMapper.Builder(client).build();
    }
    public AeroMapper getMapper() {
        //logger.info("In class " + getClass().getName() + ".getMapper()");
        return this.mapper;
    }
}

