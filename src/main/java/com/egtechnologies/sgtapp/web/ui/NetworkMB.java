/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egtechnologies.sgtapp.web.ui;

import com.egtechnologies.sgtapp.service.NetworkService;
import com.egtechnologies.sgtapp.util.JSFUtils;
import com.egtechnologies.sgtapp.web.bean.Network;
import com.egtechnologies.sgtapp.web.bean.User;
import com.egtechnologies.sgtapp.web.common.Items;
import java.io.Serializable;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Jonathan
 */
@ManagedBean
@ViewScoped
public class NetworkMB implements Serializable {
    
    private Integer searchCompany;
    private String searchName;
    private Integer idCompany;
    private String name;
    private String ipBegin;
    private String ipEnd;
    private List<Network> listNetworks;
    private Network selectedItem;

    public NetworkMB() {
        selectedItem = new Network();
    }

    public Integer getSearchCompany() {
        return searchCompany;
    }

    public void setSearchCompany(Integer searchCompany) {
        this.searchCompany = searchCompany;
    }

    public String getSearchName() {
        return searchName;
    }

    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }

    public Integer getIdCompany() {
        return idCompany;
    }

    public void setIdCompany(Integer idCompany) {
        this.idCompany = idCompany;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIpBegin() {
        return ipBegin;
    }

    public void setIpBegin(String ipBegin) {
        this.ipBegin = ipBegin;
    }

    public String getIpEnd() {
        return ipEnd;
    }

    public void setIpEnd(String ipEnd) {
        this.ipEnd = ipEnd;
    }

    public List<Network> getListNetworks() {
        return listNetworks;
    }

    public void setListNetworks(List<Network> listNetworks) {
        this.listNetworks = listNetworks;
    }

    public Network getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(Network selectedItem) {
        this.selectedItem = selectedItem;
    }
    
    @PostConstruct
    public void init() {
        try {
            NetworkService networkService = (NetworkService) JSFUtils.findBean("NetworkService");
            this.setListNetworks(networkService.getAllNetworks());
        } catch(Exception e) {
            e.getMessage();
        }
    }
    
    public void search(ActionEvent actionEvent) {
        try {
            Network network = new Network();
            network.setIdCompany(this.getSearchCompany() != null ? this.getSearchCompany() : null);
            network.setName(this.getSearchName() != null ? this.getSearchName().toUpperCase().trim() : null);
            NetworkService networkService = (NetworkService) JSFUtils.findBean("NetworkService");
            this.setListNetworks(networkService.search(network));
        } catch (Exception e) {
            e.getMessage();
        }
    }
    
    public void toSave(ActionEvent actionEvent){
        try{
            this.setIdCompany(Items.NULL_VALUE);
            this.setName(StringUtils.EMPTY);
            this.setIpBegin(StringUtils.EMPTY);
            this.setIpEnd(StringUtils.EMPTY);
            Iterator<FacesMessage> iter= FacesContext.getCurrentInstance().getMessages();
            if(iter.hasNext() == true){
                iter.remove();
                FacesContext.getCurrentInstance().renderResponse();
            }
        }catch(Exception e){
            e.getMessage();
        }
    }
    
    public void save(ActionEvent actionEvent) {
        FacesMessage message;
        try {
            User user = (User)JSFUtils.getSessionAttribute("usuario");
            Network newNetwork = new Network();
            newNetwork.setIdCompany(this.getIdCompany() != null ? this.getIdCompany() : null);
            newNetwork.setName(this.getName() != null ? this.getName().toUpperCase().trim() : null);
            newNetwork.setIpBegin(this.getIpBegin()!= null ? this.getIpBegin().toUpperCase().trim() : null);
            newNetwork.setIpEnd(this.getIpEnd()!= null ? this.getIpEnd().toUpperCase().trim() : null);
            if(!errorValidation(newNetwork)){
                NetworkService networkService = (NetworkService) JSFUtils.findBean("NetworkService");
                Network network = networkService.getNetworkByName(newNetwork);
                if(network != null){
                    message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "This name is already in use.");
                    FacesContext.getCurrentInstance().addMessage(null,message);
                }else{
                    newNetwork.setActive(Boolean.TRUE);
                    newNetwork.setCreatedDate(new Date());
                    newNetwork.setCreatedBy(user.getIdUser());
                    networkService.saveOrUpdate(newNetwork);
                    this.setListNetworks(networkService.getAllNetworks());
                    RequestContext.getCurrentInstance().execute("PF('dlg').hide();");
                }
            }
        } catch (Exception e) {
            e.getMessage();
        }
    }
    
    public void toEdit(ActionEvent actionEvent){
        try{
            Iterator<FacesMessage> iter= FacesContext.getCurrentInstance().getMessages();
            if(iter.hasNext() == true){
                iter.remove();
                FacesContext.getCurrentInstance().renderResponse();
            }
        }catch(Exception e){
            e.getMessage();
        }
    }
    
    public void edit(ActionEvent actionEvent) {
        FacesMessage message;
        try {
            User user = (User)JSFUtils.getSessionAttribute("usuario");
            Network selectedNetwork = this.getSelectedItem();
            selectedNetwork.setIdCompany(selectedNetwork.getIdCompany() != null ? selectedNetwork.getIdCompany() : null);
            selectedNetwork.setName(selectedNetwork.getName() != null ? selectedNetwork.getName().toUpperCase().trim() : null);
            selectedNetwork.setIpBegin(selectedNetwork.getIpBegin()!= null ? selectedNetwork.getIpBegin().toUpperCase().trim() : null);
            selectedNetwork.setIpEnd(selectedNetwork.getIpEnd()!= null ? selectedNetwork.getIpEnd().toUpperCase().trim() : null);
            if(!errorValidation(selectedNetwork)){
                NetworkService networkService = (NetworkService) JSFUtils.findBean("NetworkService");
                Network network = networkService.getNetworkByName(selectedNetwork);
                if(network !=null){
                    message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "This name is already in use.");
                    FacesContext.getCurrentInstance().addMessage(null,message);
                }else{
                    selectedNetwork.setModifiedDate(new Date());
                    selectedNetwork.setModifiedBy(user.getIdUser());
                    networkService.saveOrUpdate(selectedNetwork);
                    this.setListNetworks(networkService.getAllNetworks());
                    RequestContext.getCurrentInstance().execute("PF('dlgEdit').hide();");
                }
            }
        } catch (Exception e) {
            e.getMessage();
        }
    }
    
    public void disable(ActionEvent actionEvent){
        try{
            NetworkService networkService = (NetworkService) JSFUtils.findBean("NetworkService");
            getSelectedItem().setActive(Boolean.FALSE);
            networkService.saveOrUpdate(getSelectedItem());
            this.setListNetworks(networkService.getAllNetworks());
        }catch(Exception e){
            e.getMessage();
        }
    }
    
    public void enable(ActionEvent actionEvent){
        try{
            NetworkService networkService = (NetworkService) JSFUtils.findBean("NetworkService");
            getSelectedItem().setActive(Boolean.TRUE);
            networkService.saveOrUpdate(getSelectedItem());
            this.setListNetworks(networkService.getAllNetworks());
        }catch(Exception e){
            e.getMessage();
        }
    }
    
    public boolean errorValidation(Network network){
        FacesMessage message;
        boolean error = false;
        try{
            if(network.getIdCompany().equals(Items.NULL_VALUE)){
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "Select company.");
                FacesContext.getCurrentInstance().addMessage(null,message);
                error = true;
                return error;
            }
            if(StringUtils.isBlank(network.getName())){
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "Enter network's name.");
                FacesContext.getCurrentInstance().addMessage(null,message);
                error = true;
                return error;
            }
        }catch(Exception e){
            e.getMessage();
        }
        return error;
    }
}
