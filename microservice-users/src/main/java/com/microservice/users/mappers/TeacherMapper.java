package com.microservice.users.mappers;
import com.microservice.users.entities.Teacher;
import org.apache.ibatis.annotations.*;

@Mapper
public interface TeacherMapper {

    @Insert("INSERT INTO teacher (dni, name, lastname_first, lastname_second, email, phone, address) " +
            "VALUES (#{dni}, #{name}, #{lastnameFirst}, #{lastnameLast}, #{email}, #{phone}, #{address})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "idteacher")
    void createTeacher(Teacher teacher);


    //@Select("SELECT t.dni FROM teacher t WHERE t.dni = #{dni}")
    @Select("SELECT EXISTS(SELECT 1 FROM teacher WHERE dni = #{dni})")
    boolean existTeacher(String dni);

    @Delete("DELETE FROM teacher WHERE dni = #{dni}")
    boolean deleteTeacher(String dni);

    @Update("UPDATE teacher SET dni = #{dni}, name=#{name}, lastname_first=#{firstSurname}, lastname_second=#{lastSurname}, email=#{email}, phone=#{phone}, address=#{address} WHERE dni=#{dni}")
    boolean updateTeacher(Teacher teacher);

    @Select("SELECT idteacher AS id, dni, name, lastname_first AS firstSurname, lastname_second AS lastSurname, email, phone, address FROM teacher WHERE dni = #{dni}")
    Teacher getTeacher(String dni);

    @Select("SELECT idteacher AS id, dni, name, lastname_first AS firstSurname, lastname_second AS lastSurname, email, phone, address FROM teacher WHERE idteacher = #{idTeacher}")
    Teacher getTeacherById(Long idTeacher);


}
