package com.retaiontest.relationtest.servic;

import com.retaiontest.relationtest.EXception.DataInvalid;
import com.retaiontest.relationtest.EXception.NotFoundException;
import com.retaiontest.relationtest.User;
import com.retaiontest.relationtest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User registerUser(User user)throws DataInvalid {
        if (user == null) throw new DataInvalid("Il faut remplir les champs !!");
        // Enregistre l'utilisateur
        return userRepository.save(user);
    }


    public void deleteUser(Long id_user) throws DataInvalid {
        boolean existe = userRepository.existsById(id_user);
        if (!existe){
            throw new DataInvalid(
                    " User with id "+id_user+" does not exist ");
        }
        userRepository.deleteById(id_user);

    }

    public User editUser(Long id_user, User newUser){
        User existingUser = userRepository.findById(id_user)
                .orElseThrow(() -> new NotFoundException("User non trouvée avec l'ID : " + id_user));

        existingUser.setEmail(newUser.getEmail());
        existingUser.setPassword(newUser.getPassword());
        existingUser.setRole(newUser.getRole());

        return userRepository.save(existingUser);

    }

}
