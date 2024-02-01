# Rest-сервис для хранения данных о комнатах и постояльцах в хостеле

# Для запуска проекта необходимо:

1. Установить Dcoker на свой компьютер, если он еще не установлен
2. Клонировать проект из репозитория с помощью команды git clone https://github.com/quality-cell/StoringHostelData.git
3. Запустите Docker
4. Откройте проект в вашей среде разработке
5. Откройте файл StoringHostelData.java и запустите проект
6. Теперь проект готов для использования

## Endpoints
## Get
#### /guests
- Получить всех постояльцев

#### /rooms
- Получить все комнаты

#### /guests?roomId={roomId}&secondName={secondName}&name={name}&surname={surname}&gender={gender}&comfortType={comfortType}
Получить всех постояльцев с возможностью фильтрации по:
- комнате (roomId)
- фамилии (secondName)
- имени (name)
- отчеству (surname)
- полу (gender - принимает true(М)/false(Ж))
- типу комфорта комнаты (comfortType - значения по которым может быть фильтрация (STANDARD, INCREASED_COMFORT, LUX))

#### /rooms?floor={floor}&roomNumber={roomNumber}&roomType={roomType}&comfortType={comfortType}&numberOfSeats={numberOfSeats}
Получить все комнаты с возможностью фильтрации по:
- этажу (floor)
- номеру комнаты (numberRoom)
- типу комнаты (roomType - принимает true(М)/false(Ж))
- типу комфорта комнаты (comfortType - значения по которым может быть фильтрация (STANDARD, INCREASED_COMFORT, LUX))
- количеству мест (numberOfSeats - принимает true/false)

## Post
#### /add/guest/{roomId}
- Добавляет постояльца в комнату, если пол и тип комнаты равны и есть свободные места
- Пример:
{
"secondName": "Антонов",
"name": "Антон",
"surname": "Антонович",
"gender": true
}

#### /add/room
- Добавляет комнату
- Пример:
{
"floor": 1,
"roomNumber": 6,
"roomType": true,
"comfortType": "STANDARD",
"numberOfSeats": 4
}

## Patch
#### /room/{id}
- Изменить данные комнаты
- Пример:
{
"floor": 1,
"roomNumber": 6,
"roomType": true,
"comfortType": "STANDARD",
"numberOfSeats": 4
}

#### /guest/{id}
- Изменить данные постояльца (Фамилия, Имя, Отчество, Пол)
- Пример:
{
"secondName": "Антонов",
"name": "Антон",
"surname": "Антонович",
"gender": true
}

## Delete
#### /room/{id} 
- Удалить комнату

#### /guest/{id}
- Удалить постояльца
