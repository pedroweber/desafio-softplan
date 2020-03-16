import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-source-page',
  templateUrl: './source-page.component.html',
  styleUrls: ['./source-page.component.scss']
})
export class SourcePageComponent implements OnInit {

  constructor() { 
    
  }

  ngOnInit() {
    window.location.href = 'https://github.com/pedroweber/desafio-softplan';
  }

}
