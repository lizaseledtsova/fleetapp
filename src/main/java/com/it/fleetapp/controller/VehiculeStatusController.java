package com.it.fleetapp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.it.fleetapp.models.VehiculeStatus;
import com.it.fleetapp.services.VehiculeStatusService;

@Controller
public class VehiculeStatusController {


    @Autowired
    private VehiculeStatusService vehiculeStatusService;


    @GetMapping("/vehiculeStatus")
    public String getVehiculeStatus(Model model) {

        List<VehiculeStatus> vehiculeStatusList = vehiculeStatusService.getVehiculeStatus();
        model.addAttribute("vehiculeStatus", vehiculeStatusList);

        return "vehiculeStatus";
    }

    @PostMapping("/vehiculeStatus/addNew")
    public String addNewVehiculeStatus(VehiculeStatus vehiculeStatus) {
        vehiculeStatusService.saveVehiculeStatus(vehiculeStatus);
        return "redirect:/vehiculeStatus";
    }

    @RequestMapping("vehiculeStatus/findById")
    @ResponseBody
    public Optional<VehiculeStatus> findById(Integer id) {
        return vehiculeStatusService.findVehiculeStatusById(id);
    }

    @RequestMapping(value="vehiculeStatus/update", method = {RequestMethod.PUT, RequestMethod.GET})
    public String update(VehiculeStatus vehiculeStatus) {
        vehiculeStatusService.saveVehiculeStatus(vehiculeStatus);
        return "redirect:/vehiculeStatus";
    }

    @RequestMapping(value="vehiculeStatus/delete", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String delete(Integer id) {
        vehiculeStatusService.deleteVehiculeStatus(id);
        return "redirect:/vehiculeStatus";
    }


}
