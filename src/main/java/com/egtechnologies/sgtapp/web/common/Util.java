/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.egtechnologies.sgtapp.web.common;

import com.egtechnologies.sgtapp.domain.TBranchOffice;
import com.egtechnologies.sgtapp.domain.TCompany;
import com.egtechnologies.sgtapp.domain.TDepartment;
import com.egtechnologies.sgtapp.domain.TEmployee;
import com.egtechnologies.sgtapp.domain.TFacilities;
import com.egtechnologies.sgtapp.domain.THardware;
import com.egtechnologies.sgtapp.domain.THardwareType;
import com.egtechnologies.sgtapp.domain.TNetwork;
import com.egtechnologies.sgtapp.domain.TPassword;
import com.egtechnologies.sgtapp.domain.TPerson;
import com.egtechnologies.sgtapp.domain.TPosition;
import com.egtechnologies.sgtapp.domain.TRights;
import com.egtechnologies.sgtapp.domain.TRole;
import com.egtechnologies.sgtapp.domain.TSoftware;
import com.egtechnologies.sgtapp.domain.TUser;
import com.egtechnologies.sgtapp.web.bean.BranchOffice;
import com.egtechnologies.sgtapp.web.bean.Company;
import com.egtechnologies.sgtapp.web.bean.Department;
import com.egtechnologies.sgtapp.web.bean.Employee;
import com.egtechnologies.sgtapp.web.bean.Facilities;
import com.egtechnologies.sgtapp.web.bean.Hardware;
import com.egtechnologies.sgtapp.web.bean.HardwareType;
import com.egtechnologies.sgtapp.web.bean.Network;
import com.egtechnologies.sgtapp.web.bean.Password;
import com.egtechnologies.sgtapp.web.bean.Person;
import com.egtechnologies.sgtapp.web.bean.Position;
import com.egtechnologies.sgtapp.web.bean.Rights;
import com.egtechnologies.sgtapp.web.bean.Role;
import com.egtechnologies.sgtapp.web.bean.Software;
import com.egtechnologies.sgtapp.web.bean.User;
import com.sun.mail.util.MailSSLSocketFactory;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.Date;
import java.util.ResourceBundle;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author Jonatan Jacobo
 */
public class Util {

    /**
     * Envía un correo electrónico a los destinatarios dados.
     * @param to Destinatarios del correo.
     * @param subject Título del correo.
     * @param body Contenido del correo.
     */
    public static void enviarCorreo(String to[], String subject, String body){

        String mailServer;
        String portMail;
        String userMail;
        String pwdMail;
//        String useAuth = "";
        String useSSL;
        String from;
        try{
            ResourceBundle bundle = ResourceBundle.getBundle(Parameters.getMail());
            mailServer = bundle.getString("mailServer");
            portMail = bundle.getString("portMail");
            userMail = bundle.getString("userMail");
            pwdMail = bundle.getString("pwdMail");
//            useAuth = bundle.getString("useAuth");
            useSSL = bundle.getString("useSSL");
            from = bundle.getString("from");

            MimeMultipart multipart = new MimeMultipart();
            java.util.Properties props = new java.util.Properties();
            props.put("mail.smtp.host", mailServer);
            props.put("mail.smtp.port", portMail);
            props.put("mail.smtp.starttls.enable", useSSL);
            props.put("mail.smtp.user", userMail);
//            props.setProperty("mail.smtp.auth", useAuth);
            MailSSLSocketFactory sf = new MailSSLSocketFactory();
            sf.setTrustAllHosts(true);
            props.put("mail.smtp.ssl.socketFactory", sf);

            Session session = Session.getDefaultInstance(props);
            session.setDebug(true);

            Address adrss[] = new Address[to.length];
            for(int i=0;i<to.length;i++){
                adrss[i] = new InternetAddress(to[i]);
            }

            MimeMessage msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(userMail,from));
            //msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            msg.addRecipients(Message.RecipientType.TO, adrss);
            msg.setSubject(subject);
            msg.setSentDate(new Date());
            
            MimeBodyPart mbp = new MimeBodyPart();
            mbp.setContent(body, "text/html");

            multipart.addBodyPart(mbp);
            msg.setContent(multipart);

            //Transport.send(msg);
            Transport t = session.getTransport("smtp");
            t.connect(userMail,pwdMail);
            t.sendMessage(msg, msg.getAllRecipients());

        }catch(UnsupportedEncodingException | GeneralSecurityException | MessagingException e){
            e.getMessage();
        }
    }

    /**
     * Devuelve una cadena alfanumérica de seis caracteres.
     * @return clave Clave generada.
     */
    public static String generarClave(){
        String clave = null;
        int max = 99999;
        int min = 10000;
        int numeroRandom;
        int asciiRandom;
        String letraRandom;
        byte[] bytes = new byte[3];
        try{
            numeroRandom = (int) Math.floor(Math.random()*(max-min+1)+min);

            max = 122;
            min = 97;
            for(int i=0;i<3;i++){
                asciiRandom = (int) Math.floor(Math.random()*(max-min+1)+min);
                bytes[i] = (byte)asciiRandom;
            }
            letraRandom = new String(bytes);
            clave = letraRandom.concat(Integer.toString(numeroRandom));
        } catch(Exception e) {
            e.getMessage();
        }
        return clave;
    }
    
    public static Person parserPerson(TPerson tperson) {
        Person person = null;
        if(tperson != null) {
            person = new Person();
            person.setAddress(tperson.getAddress());
            person.setCellphone(tperson.getCellphone());
            person.setCity(tperson.getCity());
            person.setCreatedBy(tperson.getCreatedBy());
            person.setCreatedDate(tperson.getCreatedDate());
            person.setIdPerson(tperson.getIdPerson());
            person.setLastname(tperson.getLastname());
            person.setModifiedBy(tperson.getModifiedBy());
            person.setModifiedDate(tperson.getModifiedDate());
            person.setName(tperson.getName());
            person.setPhone(tperson.getPhone());
            person.setZipcode(tperson.getZipcode());
            person.setIdnumber(tperson.getIdnumber());
            person.setCountry(tperson.getCountry());
            person.setHomeemail(tperson.getHomeemail());
            person.setActive(tperson.getActive());
        }
        return person;
    }
    
    public static TPerson parserTPerson(Person person) {
        TPerson tperson = null;
        if(person != null) {
            tperson = new TPerson();
            tperson.setAddress(person.getAddress());
            tperson.setCellphone(person.getCellphone());
            tperson.setCity(person.getCity());
            tperson.setCreatedBy(person.getCreatedBy());
            tperson.setCreatedDate(person.getCreatedDate());
            tperson.setIdPerson(person.getIdPerson());
            tperson.setLastname(person.getLastname());
            tperson.setModifiedBy(person.getModifiedBy());
            tperson.setModifiedDate(person.getModifiedDate());
            tperson.setName(person.getName());
            tperson.setPhone(person.getPhone());
            tperson.setZipcode(person.getZipcode());
            tperson.setIdnumber(person.getIdnumber());
            tperson.setCountry(person.getCountry());
            tperson.setHomeemail(person.getHomeemail());
            tperson.setActive(person.getActive());
        }
        return tperson;
    }
    
    public static User parserUser(TUser tuser) {
        User user = null;
        if(tuser != null) {
            user = new User();
            user.setActive(tuser.getActive());
            user.setCreatedBy(tuser.getCreatedBy());
            user.setCreatedDate(tuser.getCreatedDate());
            user.setFirstlogin(tuser.getFirstlogin());
            user.setIdPerson(tuser.getIdPerson());
            user.setIdRole(tuser.getIdRole());
            user.setIdUser(tuser.getIdUser());
            user.setModifiedBy(tuser.getModifiedBy());
            user.setModifiedDate(tuser.getModifiedDate());
            user.setSendkey(tuser.getSendkey());
            user.setUsername(tuser.getUsername());
            user.setWorkemail(tuser.getWorkemail());
        }
        return user;
    }
    
    public static TUser parserTUser(User user) {
        TUser tuser = null;
        if(user != null) {
            tuser = new TUser();
            tuser.setActive(user.getActive());
            tuser.setCreatedBy(user.getCreatedBy());
            tuser.setCreatedDate(user.getCreatedDate());
            tuser.setFirstlogin(user.getFirstlogin());
            tuser.setIdPerson(user.getIdPerson());
            tuser.setIdRole(user.getIdRole());
            tuser.setIdUser(user.getIdUser());
            tuser.setModifiedBy(user.getModifiedBy());
            tuser.setModifiedDate(user.getModifiedDate());
            tuser.setSendkey(user.getSendkey());
            tuser.setUsername(user.getUsername());
            tuser.setWorkemail(user.getWorkemail());
        }
        return tuser;
    }
    
    public static Password parserPassword(TPassword tpassword) {
        Password password = null;
        if(tpassword != null) {
            password = new Password();
            password.setActive(tpassword.getActive());
            password.setCreatedBy(tpassword.getCreatedBy());
            password.setCreatedDate(tpassword.getCreatedDate());
            password.setIdPassword(tpassword.getIdPassword());
            password.setIdUser(tpassword.getIdUser());
            password.setLocked(tpassword.getLocked());
            password.setLockedDate(tpassword.getLockedDate());
            password.setModifiedBy(tpassword.getModifiedBy());
            password.setModifiedDate(tpassword.getModifiedDate());
            password.setPassword(tpassword.getPassword());
        }
        return password;
    }
    
    public static TPassword parserTPassword(Password password) {
        TPassword tpassword = null;
        if(password != null) {
            tpassword = new TPassword();
            tpassword.setActive(password.getActive());
            tpassword.setCreatedBy(password.getCreatedBy());
            tpassword.setCreatedDate(password.getCreatedDate());
            tpassword.setIdPassword(password.getIdPassword());
            tpassword.setIdUser(password.getIdUser());
            tpassword.setLocked(password.getLocked());
            tpassword.setLockedDate(password.getLockedDate());
            tpassword.setModifiedBy(password.getModifiedBy());
            tpassword.setModifiedDate(password.getModifiedDate());
            tpassword.setPassword(password.getPassword());
        }
        return tpassword;
    }
    
    public static Role parserRole(TRole trole) {
        Role role = null;
        if(trole != null) {
            role = new Role();
            role.setActive(trole.getActive());
            role.setCreatedBy(trole.getCreatedBy());
            role.setCreatedDate(trole.getCreatedDate());
            role.setIdRole(trole.getIdRole());
            role.setModifiedBy(trole.getModifiedBy());
            role.setModifiedDate(trole.getModifiedDate());
            role.setName(trole.getName());
        }
        return role;
    }
    
    public static TRole parserTRole(Role role) {
        TRole trole = null;
        if(role != null) {
            trole = new TRole();
            trole.setActive(role.getActive());
            trole.setCreatedBy(role.getCreatedBy());
            trole.setCreatedDate(role.getCreatedDate());
            trole.setIdRole(role.getIdRole());
            trole.setModifiedBy(role.getModifiedBy());
            trole.setModifiedDate(role.getModifiedDate());
            trole.setName(role.getName());
        }
        return trole;
    }
    
    public static Rights parserRights(TRights trights) {
        Rights rights = null;
        if(trights != null) {
            rights = new Rights();
            rights.setActive(trights.getActive());
            rights.setCreatedBy(trights.getCreatedBy());
            rights.setCreatedDate(trights.getCreatedDate());
            rights.setIdFacilities(trights.getIdFacilities());
            rights.setIdRights(trights.getIdRights());
            rights.setIdRole(trights.getIdRole());
            rights.setModifiedBy(trights.getModifiedBy());
            rights.setModifiedDate(trights.getModifiedDate());
        }
        return rights;
    }
    
    public static TRights parserTRights(Rights rights) {
        TRights trights = null;
        if(rights != null) {
            trights = new TRights();
            trights.setActive(rights.getActive());
            trights.setCreatedBy(rights.getCreatedBy());
            trights.setCreatedDate(rights.getCreatedDate());
            trights.setIdFacilities(rights.getIdFacilities());
            trights.setIdRights(rights.getIdRights());
            trights.setIdRole(rights.getIdRole());
            trights.setModifiedBy(rights.getModifiedBy());
            trights.setModifiedDate(rights.getModifiedDate());
        }
        return trights;
    }
    
    public static Facilities parserFacilities(TFacilities tfacilities) {
        Facilities facilities = null;
        if(tfacilities != null) {
            facilities = new Facilities();
            facilities.setActive(tfacilities.getActive());
            facilities.setCreatedBy(tfacilities.getCreatedBy());
            facilities.setCreatedDate(tfacilities.getCreatedDate());
            facilities.setDescription(tfacilities.getDescription());
            facilities.setIdFacilities(tfacilities.getIdFacilities());
            facilities.setModifiedBy(tfacilities.getModifiedBy());
            facilities.setModifiedDate(tfacilities.getModifiedDate());
            facilities.setName(tfacilities.getName());
        }
        return facilities;
    }
    
    public static TFacilities parserTFacilities(Facilities facilities) {
        TFacilities tfacilities = null;
        if(facilities != null) {
            tfacilities = new TFacilities();
            tfacilities.setActive(facilities.getActive());
            tfacilities.setCreatedBy(facilities.getCreatedBy());
            tfacilities.setCreatedDate(facilities.getCreatedDate());
            tfacilities.setDescription(facilities.getDescription());
            tfacilities.setIdFacilities(facilities.getIdFacilities());
            tfacilities.setModifiedBy(facilities.getModifiedBy());
            tfacilities.setModifiedDate(facilities.getModifiedDate());
            tfacilities.setName(facilities.getName());
        }
        return tfacilities;
    }
    
    public static HardwareType parserHardwareType(THardwareType thardwareType) {
        HardwareType hardwareType = null;
        if(thardwareType != null) {
            hardwareType = new HardwareType();
            hardwareType.setActive(thardwareType.getActive());
            hardwareType.setCreatedBy(thardwareType.getCreatedBy());
            hardwareType.setCreatedDate(thardwareType.getCreatedDate());
            hardwareType.setIdHardwareType(thardwareType.getIdHardwareType());
            hardwareType.setModifiedBy(thardwareType.getModifiedBy());
            hardwareType.setModifiedDate(thardwareType.getModifiedDate());
            hardwareType.setName(thardwareType.getName());
        }
        return hardwareType;
    }
    
    public static THardwareType parserTHardwareType(HardwareType hardwareType) {
        THardwareType thardwareType = null;
        if(hardwareType != null) {
            thardwareType = new THardwareType();
            thardwareType.setActive(hardwareType.getActive());
            thardwareType.setCreatedBy(hardwareType.getCreatedBy());
            thardwareType.setCreatedDate(hardwareType.getCreatedDate());
            thardwareType.setIdHardwareType(hardwareType.getIdHardwareType());
            thardwareType.setModifiedBy(hardwareType.getModifiedBy());
            thardwareType.setModifiedDate(hardwareType.getModifiedDate());
            thardwareType.setName(hardwareType.getName());
        }
        return thardwareType;
    }
    
    public static Company parserCompany(TCompany tcompany) {
        Company company = null;
        if(tcompany != null) {
            company = new Company();
            company.setActive(tcompany.getActive());
            company.setAddress(tcompany.getAddress());
            company.setCity(tcompany.getCity());
            company.setCountry(tcompany.getCountry());
            company.setCreatedBy(tcompany.getCreatedBy());
            company.setCreatedDate(tcompany.getCreatedDate());
            company.setEmail(tcompany.getEmail());
            company.setIdCompany(tcompany.getIdCompany());
            company.setModifiedBy(tcompany.getModifiedBy());
            company.setModifiedDate(tcompany.getModifiedDate());
            company.setName(tcompany.getName());
            company.setPhone(tcompany.getPhone());
            company.setState(tcompany.getState());
            company.setWebsite(tcompany.getWebsite());
            company.setZipcode(tcompany.getZipcode());
        }
        return company;
    }
    
    public static TCompany parserTCompany(Company company) {
        TCompany tcompany = null;
        if(company != null) {
            tcompany = new TCompany();
            tcompany.setActive(company.getActive());
            tcompany.setAddress(company.getAddress());
            tcompany.setCity(company.getCity());
            tcompany.setCountry(company.getCountry());
            tcompany.setCreatedBy(company.getCreatedBy());
            tcompany.setCreatedDate(company.getCreatedDate());
            tcompany.setEmail(company.getEmail());
            tcompany.setIdCompany(company.getIdCompany());
            tcompany.setModifiedBy(company.getModifiedBy());
            tcompany.setModifiedDate(company.getModifiedDate());
            tcompany.setName(company.getName());
            tcompany.setPhone(company.getPhone());
            tcompany.setState(company.getState());
            tcompany.setWebsite(company.getWebsite());
            tcompany.setZipcode(company.getZipcode());
        }
        return tcompany;
    }
    
    public static Position parserPosition(TPosition tposition) {
        Position position = null;
        if(tposition != null) {
            position = new Position();
            position.setActive(tposition.getActive());
            position.setCreatedBy(tposition.getCreatedBy());
            position.setCreatedDate(tposition.getCreatedDate());
            position.setDescription(tposition.getDescription());
            position.setIdCompany(tposition.getIdCompany());
            position.setIdPosition(tposition.getIdPosition());
            position.setModifiedBy(tposition.getModifiedBy());
            position.setModifiedDate(tposition.getModifiedDate());
            position.setName(tposition.getName());
        }
        return position;
    }
    
    public static TPosition parserTPosition(Position position) {
        TPosition tposition = null;
        if(position != null) {
            tposition = new TPosition();
            tposition.setActive(position.getActive());
            tposition.setCreatedBy(position.getCreatedBy());
            tposition.setCreatedDate(position.getCreatedDate());
            tposition.setDescription(position.getDescription());
            tposition.setIdCompany(position.getIdCompany());
            tposition.setIdPosition(position.getIdPosition());
            tposition.setModifiedBy(position.getModifiedBy());
            tposition.setModifiedDate(position.getModifiedDate());
            tposition.setName(position.getName());
        }
        return tposition;
    }
    
    public static Network parserNetwork(TNetwork tnetwork) {
        Network network = null;
        if(tnetwork != null) {
            network = new Network();
            network.setActive(tnetwork.getActive());
            network.setCreatedBy(tnetwork.getCreatedBy());
            network.setCreatedDate(tnetwork.getCreatedDate());
            network.setIdCompany(tnetwork.getIdCompany());
            network.setIdNetwork(tnetwork.getIdNetwork());
            network.setIpBegin(tnetwork.getIpBegin());
            network.setIpEnd(tnetwork.getIpEnd());
            network.setModifiedBy(tnetwork.getModifiedBy());
            network.setModifiedDate(tnetwork.getModifiedDate());
            network.setName(tnetwork.getName());
        }
        return network;
    }
    
    public static TNetwork parserTNetwork(Network network) {
        TNetwork tnetwork = null;
        if(network != null) {
            tnetwork = new TNetwork();
            tnetwork.setActive(network.getActive());
            tnetwork.setCreatedBy(network.getCreatedBy());
            tnetwork.setCreatedDate(network.getCreatedDate());
            tnetwork.setIdCompany(network.getIdCompany());
            tnetwork.setIdNetwork(network.getIdNetwork());
            tnetwork.setIpBegin(network.getIpBegin());
            tnetwork.setIpEnd(network.getIpEnd());
            tnetwork.setModifiedBy(network.getModifiedBy());
            tnetwork.setModifiedDate(network.getModifiedDate());
            tnetwork.setName(network.getName());
        }
        return tnetwork;
    }
    
    public static Software parserSoftware(TSoftware tsoftware) {
        Software software = null;
        if(tsoftware != null) {
            software = new Software();
            software.setActive(tsoftware.getActive());
            software.setBrand(tsoftware.getBrand());
            software.setCreatedBy(tsoftware.getCreatedBy());
            software.setCreatedDate(tsoftware.getCreatedDate());
            software.setDescription(tsoftware.getDescription());
            software.setIdCompany(tsoftware.getIdCompany());
            software.setIdSoftware(tsoftware.getIdSoftware());
            software.setLicenseCount(tsoftware.getLicenseCount());
            software.setModifiedBy(tsoftware.getModifiedBy());
            software.setModifiedDate(tsoftware.getModifiedDate());
            software.setName(tsoftware.getName());
            software.setSupportEmail(tsoftware.getSupportEmail());
            software.setSupportPhone(tsoftware.getSupportPhone());
        }
        return software;
    }
    
    public static TSoftware parserTSoftware(Software software) {
        TSoftware tsoftware = null;
        if(software != null) {
            tsoftware = new TSoftware();
            tsoftware.setActive(software.getActive());
            tsoftware.setBrand(software.getBrand());
            tsoftware.setCreatedBy(software.getCreatedBy());
            tsoftware.setCreatedDate(software.getCreatedDate());
            tsoftware.setDescription(software.getDescription());
            tsoftware.setIdCompany(software.getIdCompany());
            tsoftware.setIdSoftware(software.getIdSoftware());
            tsoftware.setLicenseCount(software.getLicenseCount());
            tsoftware.setModifiedBy(software.getModifiedBy());
            tsoftware.setModifiedDate(software.getModifiedDate());
            tsoftware.setName(software.getName());
            tsoftware.setSupportEmail(software.getSupportEmail());
            tsoftware.setSupportPhone(software.getSupportPhone());
        }
        return tsoftware;
    }
    
    public static BranchOffice parserBranchOffice(TBranchOffice tbranchOffice) {
        BranchOffice branchOffice = null;
        if(tbranchOffice != null) {
            branchOffice = new BranchOffice();
            branchOffice.setActive(tbranchOffice.getActive());
            branchOffice.setAddress(tbranchOffice.getAddress());
            branchOffice.setCity(tbranchOffice.getCity());
            branchOffice.setCountry(tbranchOffice.getCountry());
            branchOffice.setCreatedBy(tbranchOffice.getCreatedBy());
            branchOffice.setCreatedDate(tbranchOffice.getCreatedDate());
            branchOffice.setIdBranchOffice(tbranchOffice.getIdBranchOffice());
            branchOffice.setIdCompany(tbranchOffice.getIdCompany());
            branchOffice.setModifiedBy(tbranchOffice.getModifiedBy());
            branchOffice.setModifiedDate(tbranchOffice.getModifiedDate());
            branchOffice.setName(tbranchOffice.getName());
            branchOffice.setPhone(tbranchOffice.getPhone());
            branchOffice.setState(tbranchOffice.getState());
            branchOffice.setZipcode(tbranchOffice.getZipcode());
        }
        return branchOffice;
    }
    
    public static TBranchOffice parserTBranchOffice(BranchOffice branchOffice) {
        TBranchOffice tbranchOffice = null;
        if(branchOffice != null) {
            tbranchOffice = new TBranchOffice();
            tbranchOffice.setActive(branchOffice.getActive());
            tbranchOffice.setAddress(branchOffice.getAddress());
            tbranchOffice.setCity(branchOffice.getCity());
            tbranchOffice.setCountry(branchOffice.getCountry());
            tbranchOffice.setCreatedBy(branchOffice.getCreatedBy());
            tbranchOffice.setCreatedDate(branchOffice.getCreatedDate());
            tbranchOffice.setIdBranchOffice(branchOffice.getIdBranchOffice());
            tbranchOffice.setIdCompany(branchOffice.getIdCompany());
            tbranchOffice.setModifiedBy(branchOffice.getModifiedBy());
            tbranchOffice.setModifiedDate(branchOffice.getModifiedDate());
            tbranchOffice.setName(branchOffice.getName());
            tbranchOffice.setPhone(branchOffice.getPhone());
            tbranchOffice.setState(branchOffice.getState());
            tbranchOffice.setZipcode(branchOffice.getZipcode());
        }
        return tbranchOffice;
    }
    
    public static Department parserDepartment(TDepartment tdepartment) {
        Department department = null;
        if(tdepartment != null) {
            department = new Department();
            department.setActive(tdepartment.getActive());
            department.setCreatedBy(tdepartment.getCreatedBy());
            department.setCreatedDate(tdepartment.getCreatedDate());
            department.setDescription(tdepartment.getDescription());
            department.setIdBranchOffice(tdepartment.getIdBranchOffice());
            department.setIdCompany(tdepartment.getIdCompany());
            department.setIdDepartment(tdepartment.getIdDepartment());
            department.setModifiedBy(tdepartment.getModifiedBy());
            department.setModifiedDate(tdepartment.getModifiedDate());
            department.setName(tdepartment.getName());
        }
        return department;
    }
    
    public static TDepartment parserTDepartment(Department department) {
        TDepartment tdepartment = null;
        if(department != null) {
            tdepartment = new TDepartment();
            tdepartment.setActive(department.getActive());
            tdepartment.setCreatedBy(department.getCreatedBy());
            tdepartment.setCreatedDate(department.getCreatedDate());
            tdepartment.setDescription(department.getDescription());
            tdepartment.setIdBranchOffice(department.getIdBranchOffice());
            tdepartment.setIdCompany(department.getIdCompany());
            tdepartment.setIdDepartment(department.getIdDepartment());
            tdepartment.setModifiedBy(department.getModifiedBy());
            tdepartment.setModifiedDate(department.getModifiedDate());
            tdepartment.setName(department.getName());
        }
        return tdepartment;
    }
    
    public static Employee parserEmployee(TEmployee temployee) {
        Employee employee = null;
        if(temployee != null) {
            employee = new Employee();
            employee.setActive(temployee.getActive());
            employee.setAddress(temployee.getAddress());
            employee.setCellphone(temployee.getCellphone());
            employee.setCity(temployee.getCity());
            employee.setCountry(temployee.getCountry());
            employee.setCreatedBy(temployee.getCreatedBy());
            employee.setCreatedDate(temployee.getCreatedDate());
            employee.setHomeemail(temployee.getHomeemail());
            employee.setIdBranchOffice(temployee.getIdBranchOffice());
            employee.setIdCompany(temployee.getIdCompany());
            employee.setIdDepartment(temployee.getIdDepartment());
            employee.setIdEmployee(temployee.getIdEmployee());
            employee.setIdPosition(temployee.getIdPosition());
            employee.setCode(temployee.getCode());
            employee.setLastname(temployee.getLastname());
            employee.setModifiedBy(temployee.getModifiedBy());
            employee.setModifiedDate(temployee.getModifiedDate());
            employee.setName(temployee.getName());
            employee.setPhone(temployee.getPhone());
            employee.setState(temployee.getState());
            employee.setZipcode(temployee.getZipcode());
        }
        return employee;
    }
    
    public static TEmployee parserTEmployee(Employee employee) {
        TEmployee temployee = null;
        if(employee != null) {
            temployee = new TEmployee();
            temployee.setActive(employee.getActive());
            temployee.setAddress(employee.getAddress());
            temployee.setCellphone(employee.getCellphone());
            temployee.setCity(employee.getCity());
            temployee.setCountry(employee.getCountry());
            temployee.setCreatedBy(employee.getCreatedBy());
            temployee.setCreatedDate(employee.getCreatedDate());
            temployee.setHomeemail(employee.getHomeemail());
            temployee.setIdBranchOffice(employee.getIdBranchOffice());
            temployee.setIdCompany(employee.getIdCompany());
            temployee.setIdDepartment(employee.getIdDepartment());
            temployee.setIdEmployee(employee.getIdEmployee());
            temployee.setIdPosition(employee.getIdPosition());
            temployee.setCode(employee.getCode());
            temployee.setLastname(employee.getLastname());
            temployee.setModifiedBy(employee.getModifiedBy());
            temployee.setModifiedDate(employee.getModifiedDate());
            temployee.setName(employee.getName());
            temployee.setPhone(employee.getPhone());
            temployee.setState(employee.getState());
            temployee.setZipcode(employee.getZipcode());
        }
        return temployee;
    }
    
    public static Hardware parserHardware(THardware thardware) {
        Hardware hardware = null;
        if(thardware != null) {
            hardware = new Hardware();
            hardware.setActive(thardware.getActive());
            hardware.setBrand(thardware.getBrand());
            hardware.setCreatedBy(thardware.getCreatedBy());
            hardware.setCreatedDate(thardware.getCreatedDate());
            hardware.setIdCompany(thardware.getIdCompany());
            hardware.setIdHardware(thardware.getIdHardware());
            hardware.setIdHardwareType(thardware.getIdHardwareType());
            hardware.setModel(thardware.getModel());
            hardware.setModifiedBy(thardware.getModifiedBy());
            hardware.setModifiedDate(thardware.getModifiedDate());
            hardware.setSerialNumber(thardware.getSerialNumber());
        }
        return hardware;
    }
    
    public static THardware parserTHardware(Hardware hardware) {
        THardware thardware = null;
        if(hardware != null) {
            thardware = new THardware();
            thardware.setActive(hardware.getActive());
            thardware.setBrand(hardware.getBrand());
            thardware.setCreatedBy(hardware.getCreatedBy());
            thardware.setCreatedDate(hardware.getCreatedDate());
            thardware.setIdCompany(hardware.getIdCompany());
            thardware.setIdHardware(hardware.getIdHardware());
            thardware.setIdHardwareType(hardware.getIdHardwareType());
            thardware.setModel(hardware.getModel());
            thardware.setModifiedBy(hardware.getModifiedBy());
            thardware.setModifiedDate(hardware.getModifiedDate());
            thardware.setSerialNumber(hardware.getSerialNumber());
        }
        return thardware;
    }
}
