package fr.univrouen.stb23v1.services;

import fr.univrouen.stb23v1.entities.Member;
import fr.univrouen.stb23v1.repositories.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Member save(Member member) {
        return memberRepository.save(member);
    }
}
