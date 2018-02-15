/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egtechnologies.sgtapp.web.ui;

import com.egtechnologies.sgtapp.service.PositionService;
import com.egtechnologies.sgtapp.util.JSFUtils;
import com.egtechnologies.sgtapp.web.bean.Position;
import com.egtechnologies.sgtapp.web.bean.User;
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
public class PositionMB implements Serializable {
    
    private Integer searchCompany;
    private String searchName;
    private Integer idCompany;
    private String name;
    private String description;
    private List<Position> listPositions;
    private Position selectedItem;
    
    /** Creates a new instance of PositionMB */
    public PositionMB() {
        selectedItem = new Position();
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Position> getListPositions() {
        return listPositions;
    }

    public void setListPositions(List<Position> listPositions) {
        this.listPositions = listPositions;
    }

    public Position getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(Position selectedItem) {
        this.selectedItem = selectedItem;
    }
    
    @PostConstruct
    public void init() {
        try {
            PositionService positionService = (PositionService) JSFUtils.findBean("PositionService");
            this.setListPositions(positionService.getAllPositions());
        } catch(Exception e) {
            e.getMessage();
        }
    }
    
    public void search(ActionEvent actionEvent) {
        try {
            Position position = new Position();
            position.setIdCompany(this.getSearchCompany() != null ? this.getSearchCompany() : null);
            position.setName(this.getSearchName() != null ? this.getSearchName().toUpperCase().trim() : null);
            PositionService positionService = (PositionService) JSFUtils.findBean("PositionService");
            this.setListPositions(positionService.search(position));
        } catch (Exception e) {
            e.getMessage();
        }
    }
    
    public void toSave(ActionEvent actionEvent){
        try{
            this.setIdCompany(null);
            this.setName(StringUtils.EMPTY);
            this.setDescription(StringUtils.EMPTY);
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
            Position newPosition = new Position();
            newPosition.setIdCompany(this.getIdCompany() != null ? this.getIdCompany() : null);
            newPosition.setName(this.getName() != null ? this.getName().toUpperCase().trim() : null);
            newPosition.setDescription(this.getDescription()!= null ? this.getDescription().toUpperCase().trim() : null);
            if(!errorValidation(newPosition)){
                PositionService positionService = (PositionService) JSFUtils.findBean("PositionService");
                Position position = positionService.getPositionByName(newPosition);
                if(position != null){
                    message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "This name is already in use.");
                    FacesContext.getCurrentInstance().addMessage(null,message);
                }else{
                    newPosition.setActive(Boolean.TRUE);
                    newPosition.setCreatedDate(new Date());
                    newPosition.setCreatedBy(user.getIdUser());
                    positionService.saveOrUpdate(newPosition);
                    this.setListPositions(positionService.getAllPositions());
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
            Position selectedPosition = this.getSelectedItem();
            selectedPosition.setIdCompany(selectedPosition.getIdCompany() != null ? selectedPosition.getIdCompany() : null);
            selectedPosition.setName(selectedPosition.getName() != null ? selectedPosition.getName().toUpperCase().trim() : null);
            selectedPosition.setDescription(selectedPosition.getDescription()!= null ? selectedPosition.getDescription().toUpperCase().trim() : null);
            if(!errorValidation(selectedPosition)){
                PositionService positionService = (PositionService) JSFUtils.findBean("PositionService");
                Position position = positionService.getPositionByName(selectedPosition);
                if(position !=null){
                    message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "This name is already in use.");
                    FacesContext.getCurrentInstance().addMessage(null,message);
                }else{
                    selectedPosition.setModifiedDate(new Date());
                    selectedPosition.setModifiedBy(user.getIdUser());
                    positionService.saveOrUpdate(selectedPosition);
                    this.setListPositions(positionService.getAllPositions());
                    RequestContext.getCurrentInstance().execute("PF('dlgEdit').hide();");
                }
            }
        } catch (Exception e) {
            e.getMessage();
        }
    }
    
    public void disable(ActionEvent actionEvent){
        try{
            PositionService positionService = (PositionService) JSFUtils.findBean("PositionService");
            getSelectedItem().setActive(Boolean.FALSE);
            positionService.saveOrUpdate(getSelectedItem());
            this.setListPositions(positionService.getAllPositions());
        }catch(Exception e){
            e.getMessage();
        }
    }
    
    public void enable(ActionEvent actionEvent){
        try{
            PositionService positionService = (PositionService) JSFUtils.findBean("PositionService");
            getSelectedItem().setActive(Boolean.TRUE);
            positionService.saveOrUpdate(getSelectedItem());
            this.setListPositions(positionService.getAllPositions());
        }catch(Exception e){
            e.getMessage();
        }
    }
    
    public boolean errorValidation(Position position){
        FacesMessage message;
        boolean error = false;
        try{
            if(position.getIdCompany() != null){
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "Select company.");
                FacesContext.getCurrentInstance().addMessage(null,message);
                error = true;
                return error;
            }
            if(StringUtils.isBlank(position.getName())){
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR.", "Enter position's name.");
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
