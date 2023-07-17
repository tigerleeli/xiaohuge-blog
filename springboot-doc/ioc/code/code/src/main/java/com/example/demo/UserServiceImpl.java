package com.example.demo;

@Component
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public String getInfo() {
        return userRepository.getInfo();
    }
}
