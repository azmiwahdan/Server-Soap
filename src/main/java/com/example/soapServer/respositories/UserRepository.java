package com.example.soapServer.respositories;

import com.example.serverone.User;
import org.springframework.data.aerospike.repository.AerospikeRepository;

public interface UserRepository extends AerospikeRepository<User,Integer> {
}
