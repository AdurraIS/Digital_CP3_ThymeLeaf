package com.cp3.apicp3.Controllers;

import com.cp3.apicp3.Model.Car;
import com.cp3.apicp3.Services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cars")
public class CarController {

    @Autowired
    private CarService carService;

    //Ao entrar no endpoint /cars/ com GET essa função envia
    // o usuario a pagina lista de carros
    @GetMapping
    public String listProduct(Model model) {
        model.addAttribute("cars", carService.findAll());
        return "cars/list";
    }

    //Ao clicar em criar carro a função envia o usuario para o formulario
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("car", new Car());
        return "cars/form";
    }
    //Ao clicar em enviar o produto é salvo e o usuario é enviado de volta
    //para o endpoint inicial GET /cars/
    @PostMapping
    public String saveProduct(@ModelAttribute Car car) {
        carService.save(car);
        return "redirect:cars";
    }

    //Ao clicar em editar a função vai redirecionar ao formulario novamente
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Car car = carService.findById(id).orElseThrow(() -> new IllegalArgumentException("Id do carro invalido: " + id));
        model.addAttribute("car", car);
        return "cars/form";
    }
    //Ao clicar em enviar a função vai salvar as mudanças e redirecionar a
    //pagina inicial
    @GetMapping("/update/{id}")
    public String updateCar(@PathVariable Long id, @ModelAttribute Car car) {
        carService.save(car);
        return "redirect:/cars";
    }

    //Ao clicar em remover a função redireciona a pagina principal para atualizar
    // a lista
    @GetMapping("/delete/{id}")
    public String deleteCar(@PathVariable Long id) {
        carService.deleteById(id);
        return "redirect:/cars";
    }
}
