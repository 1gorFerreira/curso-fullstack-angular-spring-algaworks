import { Component } from '@angular/core';

@Component({
  selector: 'app-persons-search',
  templateUrl: './persons-search.component.html',
  styleUrl: './persons-search.component.css'
})
export class PersonsSearchComponent {
  persons = [
    { name: 'Manoel Pinheiro', city: 'Uberlândia', state: 'MG', active: true },
    { name: 'Sebastião da Silva', city: 'São Paulo', state: 'SP', active: false },
    { name: 'Carla Souza', city: 'Florianópolis', state: 'SC', active: true },
    { name: 'Luís Pereira', city: 'Curitiba', state: 'PR', active: true },
    { name: 'Vilmar Andrade', city: 'Rio de Janeiro', state: 'RJ', active: false },
    { name: 'Paula Maria', city: 'Uberlândia', state: 'MG', active: true }
  ];
}
