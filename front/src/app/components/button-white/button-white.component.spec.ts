import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ButtonWhiteComponent } from './button-white.component';

describe('ButtonWhiteComponent', () => {
  let component: ButtonWhiteComponent;
  let fixture: ComponentFixture<ButtonWhiteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ButtonWhiteComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ButtonWhiteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
